#!/usr/bin/env python
#encoding=utf-8
import subprocess
import json
import time
import sys
import os
import logging
import re
import datetime
from logging.handlers import TimedRotatingFileHandler


from logging.handlers import TimedRotatingFileHandler
reload(sys)
sys.setdefaultencoding('utf-8')
CurrentPtah = os.path.split( os.path.realpath( sys.argv[0] ) )[0]
handler = TimedRotatingFileHandler(CurrentPtah+'/logs/etl_log',when="D",interval=1,backupCount=7)
handler.suffix = "%Y-%m-%d.log"
handler.extMatch = re.compile(r"^\d{4}-\d{2}-\d{2}.log$")
log_fmt = '%(asctime)s\tFile \"%(filename)s\",line %(lineno)s - %(levelname)s: %(message)s'
formatter = logging.Formatter(log_fmt)
handler.setFormatter(formatter)
logging.basicConfig(level=logging.INFO)
log = logging.getLogger()
log.addHandler(handler)


#需要算3天的数据,需要从参数1得到参数2的时间,时间由java传入,

dt = sys.argv[1]
time.strptime(dt,'%Y-%m-%d')

#定义python的参数job_id
#job_id=sys.argv[2]
#err="erretl:"

try:
    time.strptime(dt,'%Y-%m-%d')
except ValueError as e:
    log.info( 'time data %s does not match format "yyyy-MM-dd"' % dt)
    exit(1)

process_pubsuit = 's3://pub-dataprocess-virginia/email_dataprocess/thirdpartyemail/platform=pubsuite/pubsuit_tab/hive_script/'

def main():
    try:

        #load_msyql2s3(dt)
        log.info("start")
        clusterId = create_emr_cluster()
        log.info("start_a")

        if clusterId and check_allsteps_completed(clusterId):
            log.info( '------------------ the result data has been in s3-----------------' )
            hive(clusterId)

            log.info('----the data has been processed------------------------------')


    except BaseException as e:
        log.error( e )

        #log_etljob(job_id,err)

    #成功后写入到数据库
    else :
        print ("error")
        #log_etljob(job_id,dt)

    finally:
        #terminate_emrcluster_byclusterId(clusterId)
        log.info( '------------------ end process-----------------' )

#创建集群
def create_emr_cluster():
   #createEmrCluster = 'aws emr create-cluster --name ssp-emr-'+dt+' --release-label emr-5.0.0  --service-role EMR_DefaultRole --ec2-attributes  KeyName=Defy-feeds-data-process-virginia,InstanceProfile=EMR_EC2_DefaultRole,AvailabilityZone=us-east-1d --log-uri s3://pub-dataprocess-virginia/dataprocess_log/emr-hive/  --instance-groups \'[{"InstanceCount": 1,"BidPrice":"0.05","Name": "masterNode","InstanceGroupType": "MASTER","InstanceType": "m3.xlarge"},{"InstanceCount": 1,"BidPrice":"0.05","Name": "coreNode","InstanceGroupType": "CORE","InstanceType": "m3.xlarge"},{"InstanceCount": 1,"BidPrice":"0.05","Name": "taskNode","InstanceGroupType": "TASK","InstanceType": "m3.xlarge"}]\' --applications Name=Hadoop Name=Hive Name=SQOOP --steps  Type=HIVE,Name=\'Hive_Process_pubsuit\',ActionOnFailure=TERMINATE_CLUSTER,Args=[-f,s3://pub-dataprocess-virginia/email_dataprocess/thirdpartyemail/platform=pubsuite/pubsuit_tab/hive_script/pubsuit.q,] --no-termination-protected --enable-debugging --profile seed '
   createEmrCluster = 'aws emr create-cluster --name ssp-emr-'+dt+' --release-label emr-5.0.0  --service-role EMR_DefaultRole --ec2-attributes  KeyName=Defy-feeds-data-process-virginia,InstanceProfile=EMR_EC2_DefaultRole,AvailabilityZone=us-east-1d --log-uri s3://pub-dataprocess-virginia/dataprocess_log/emr-hive/  --instance-groups \'[{"InstanceCount": 1,"BidPrice":"0.05","Name": "masterNode","InstanceGroupType": "MASTER","InstanceType": "m3.xlarge"}] \' --applications Name=Hadoop Name=Hive Name=SQOOP  --no-termination-protected --enable-debugging --profile seed '
   emrClusterResp = subprocess.Popen(createEmrCluster,shell=True,stdout=subprocess.PIPE,stderr=subprocess.STDOUT)
   create_output = ""
   emrClusterResp.wait()
   for line in emrClusterResp.stdout.readlines():
        create_output = create_output + line
   log.info( create_output )
   emrClusterResp_reval = json.loads(create_output)
   clusterId = emrClusterResp_reval['ClusterId']
   log.info( 'clusterId -----> %s' % clusterId )
   return clusterId

#调用hive脚本,每次重新算昨天,前天,大前天的数据
def hive(clusterId):
    #上传脚本到s3
    put_hql='aws s3 cp '+CurrentPtah+'/pubsuit.q  ' +process_pubsuit + '--profile seed --region us-east-1'
    # put_hql2='aws s3 cp '+CurrentPtah+'/pubsuit_result.q  ' +process_pubsuit + '--profile seed --region us-east-1'
    subprocess.Popen(put_hql,shell=True,stdout=subprocess.PIPE,stderr=subprocess.STDOUT).wait()
    # subprocess.Popen(put_hql2,shell=True,stdout=subprocess.PIPE,stderr=subprocess.STDOUT).wait()
    #得到文件从远程s3
    subprocess.Popen('chmod 600 '+CurrentPtah+'/Defy-feeds-data-process.pem',shell=True,stdout=subprocess.PIPE,stderr=subprocess.STDOUT).wait()
    #scp -i xxxx-key-pair.pem /home/ubunut/doc/test.xml sky@192.0.0.1:/home/ubuntu/
    #wgethsql = 'scp -i '+CurrentPtah+'/Defy-feeds-data-process-virginia.pem '+CurrentPtah+'/pubsuit.q  '+'hadoop@'+clusterId+':'+'/home/hadoop/'

    wgethsql='aws emr ssh --cluster-id '+clusterId+' --key-pair-file '+CurrentPtah+'/Defy-feeds-data-process-virginia.pem --command "hadoop fs -get s3://pub-dataprocess-virginia/email_dataprocess/thirdpartyemail/platform=pubsuite/pubsuit_tab/hive_script/pubsuit.q" --profile seed --region us-east-1'
   # wgethsql2='aws emr ssh --cluster-id '+clusterId+' --key-pair-file '+CurrentPtah+'/Defy-feeds-data-process-virginia.pem --command "hadoop fs -get s3://pub-dataprocess-virginia/email_dataprocess/thirdpartyemail/platform=pubsuite/pubsuit_tab/hive_script/pubsuit_result.q" --profile seed --region us-east-1'
    print(wgethsql.lower());
   #print(wgethsql2.lower());
    #wgethsql = 'scp -i  --cluster-id '+clusterId+' --key-pair-file '+CurrentPtah+'/Defy-feeds-data-process-virginia.pem --command "wget http://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-5.1.38.tar.gz" --profile seed '
    subprocess.Popen(wgethsql,shell=True,stdout=subprocess.PIPE,stderr=subprocess.STDOUT).wait()
    #subprocess.Popen(wgethsql2,shell=True,stdout=subprocess.PIPE,stderr=subprocess.STDOUT).wait()
    #得到文件脚本ec2上的

    log.info('-----the data be process in s3 using hive----------------------')

    #cmd ='aws emr ssh --cluster-id '+clusterId+' --key-pair-file '+CurrentPtah+'/Defy-feeds-data-process-virginia.pem --command "hive -hivevar dst=dt_str -f /home/hadoop/pubsuit.q" --profile seed --region us-east-1 '
    #print(cmd.lower());


    # 获取当前日期now
    #now = datetime.datetime.now()
    #now = now.strftime('%Y-%m-%d')
    #now = datetime.datetime.strptime(now, '%Y-%m-%d')

    # 需要处理的日期,传入的时间
    dt_datetime = datetime.datetime.strptime(dt, '%Y-%m-%d')
    #dt_datetime = dt_datetime + datetime.timedelta(days = -3)
    dt_datet=dt_datetime + datetime.timedelta(days = -3)

    #把结果表插入到s3目录里需要的起始时间,da其实为dt
    #da=dt_datetime;
    #db=dt_datet;

    # 处理的日期与当前服务器日期比较
    #delta=now - dt_datetime
    #delta=dt_datetime-dt_datet
    # 只有当处理的日期
    #while delta.days >= 0:
        #dt_str = dt_datetime.strftime('%Y-%m-%d')

    #三天前的时间
    dt_str= dt_datet.strftime('%Y-%m-%d')
    log.info("start process s3 data in hive")
        #hive -hivevar dst="2017-07-02" -f /home/hadoop/pubsuit.q

    cmd ='aws emr ssh --cluster-id '+clusterId+' --key-pair-file '+CurrentPtah+'/Defy-feeds-data-process-virginia.pem --command " hive -hivevar dst="{}"  -hivevar pro_day="{}"'.format(dt_str,dt_datetime) +' -f /home/hadoop/pubsuit.q" --profile seed --region us-east-1 '
    print(cmd.lower());

        # p = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE,stderr=subprocess.STDOUT)
        # stdout,stderr = p.communicate()
        # if p.returncode != 0:
        #     print stderr
        #     sys.exit(1)
        # log.info("start process s3 data in hive  for next time")



        #p.wait()
    log.info("start process s3 data in hive  for nextnext time")

    #处理的日期 +1天
    #oneday=datetime.timedelta(days=1)
    #dt_datetime = dt_datetime + oneday
    #dt_datet=dt_datet+ oneday
    #delta=now - dt_datetime
    #delta=dt_datetime- dt_datet





def check_allsteps_completed(clusterId):
    while True:
        time.sleep(30)
        allSteps = subprocess.Popen('aws emr list-steps --cluster-id '+clusterId+' --profile seed --region us-east-1',shell=True,stdout=subprocess.PIPE,stderr=subprocess.STDOUT)
        allSteps.wait()
        console = ""
        for line in allSteps.stdout.readlines():
            console = console + line

        allSteps_reval = json.loads(console)
        steps = allSteps_reval['Steps']
        completeSteps = []
        faildSteps = []
        runningAndPendingSteps = []
        for s in steps:
            if s['Status']['State'] in ['COMPLETED']:
                completeSteps.append(s)
            elif s['Status']['State'] in ['FAILED','CANCELLED','INTERRUPTED']:
                faildSteps.append(s)
            elif s['Status']['State'] in ['RUNNING','PENDING']:
                runningAndPendingSteps.append(s)
            else:
                pass
        log.info( '------------------------------  All Step  -----------------------------------------' )
        if len(completeSteps) > 0:
            printStepState(completeSteps)
            if len(completeSteps) == len(steps):
                return True
        if len(faildSteps) > 0:
            printStepState(faildSteps)
            return False
        if len(runningAndPendingSteps) > 0:
            printStepState(runningAndPendingSteps)


def terminate_emrcluster_byclusterId(clusterId):
    terminatecluster = 'aws emr terminate-clusters --cluster-id '+clusterId+'  --profile seed --region us-east-1'
    subprocess.Popen(terminatecluster,shell=True,stdout=subprocess.PIPE,stderr=subprocess.STDOUT).wait()
    log.info( 'emr cluster is terminating , id: %s' % clusterId )


def printStepState(step_list):
    for step in step_list:
        log.info( 'step name[%s]\tid[%s]\tstatus[%s]' % (step['Name'] , step['Id'] , step['Status']['State']) )

#路径修改
def load_msyql2s3(dt):
    java_command = 'java -cp '+'/once_work/git/ssp/hive/hive_dataprocess/target/hiveUtil-1.0-SNAPSHOT-jar-with-dependencies.jar '+'Utiltool.mysql2s3 '+dt
    log.info( 'excute : %s' % java_command )
    subprocess.Popen(java_command,shell=True,stdout=subprocess.PIPE,stderr=subprocess.STDOUT)

#to_do
def log_etljob(job_id,dt_url):
    #java_command='java -cp '+'/once_work/git/ssp/hive/hive_dataprocess/target/hiveUtil-1.0-SNAPSHOT-jar-with-dependencies.jar '+'Utiltool.etllog2db ' +job_id+','+dt_url
    # log.info( 'excute : %s' % java_command )
    # subprocess.Popen(java_command,shell=True,stdout=subprocess.PIPE,stderr=subprocess.STDOUT)
     log.info('excute has bee compliete')

if __name__ == '__main__':
    main()

