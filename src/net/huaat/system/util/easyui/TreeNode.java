package net.huaat.system.util.easyui;

import java.util.List;

/**  
 * @Description: TODO
 * @author zhiqiang yang 
 * @date 2013-3-4 下午1:50:34
 * @version V1.0  
 */
public class TreeNode {
	public String id;  
    public String text;  
    /**
     * open or closed<br>
     * IF the node has children, set the state to 'closed' so the node can asynchronous load children nodes 
     */
    public String state="open";
    
    private List<TreeNode> children;
    
    public int depth=0;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
    
}

