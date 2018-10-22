package ${packageName};

import net.huaat.constants.ModelFunctionConstants; 
import net.huaat.util.Pojo;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

public class ${className} implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	<#list commFields?if_exists as o>
	private ${o.type} ${o.name};
	</#list>
	
	<#list commFields?if_exists as o>
	public ${o.type} get${o.name?cap_first}() {
		return ${o.name};
	}

	public void set${o.name?cap_first}(${o.type} ${o.name}) {
		this.${o.name} = ${o.name};
	}
	</#list>
	
	<#list funFields?if_exists as o>
	private ${o.tRuleModelFiled.type} ${o.tRuleModelFiled.name};
	</#list>
	
	<#list funFields?if_exists as o>
	public ${o.tRuleModelFiled.type} get${o.tRuleModelFiled.name?cap_first}() {
		List<Pojo> list = new ArrayList<Pojo>();
		Pojo obj = null;
		
		<#list o.tModelFunSources?if_exists as o2>
		obj = new Pojo();
		obj.setKey("${o2.tRuleModelFiled.name}");
		<#--区分数字与字符 -->
		<#if o2.tRuleModelFiled.type=="String">
		obj.setValue(get${o2.tRuleModelFiled.name?cap_first}());
		<#elseif o2.tRuleModelFiled.type=="BigDecimal">
		obj.setValue(get${o2.tRuleModelFiled.name?cap_first}());
		<#else>
		obj.setValue(new BigDecimal(get${o2.tRuleModelFiled.name?cap_first}()));
		</#if>
		list.add(obj);
		</#list>
		
		<#if o.tRuleModelFiled.type=="String">
		return ModelFunctionConstants.${o.funId}(list,"${o.funValueType}").toString();
		</#if>
		<#if o.tRuleModelFiled.type=="Integer">
		return ((BigDecimal)ModelFunctionConstants.${o.funId}(list,"${o.funValueType}")).intValue();
		</#if>
		<#if o.tRuleModelFiled.type=="BigDecimal">
		return (BigDecimal)ModelFunctionConstants.${o.funId}(list,"${o.funValueType}");
		</#if>
	}

	public void set${o.tRuleModelFiled.name?cap_first}(${o.tRuleModelFiled.type} ${o.tRuleModelFiled.name}) {
		this.${o.tRuleModelFiled.name} = ${o.tRuleModelFiled.name};
	}
	</#list>
	
	
}