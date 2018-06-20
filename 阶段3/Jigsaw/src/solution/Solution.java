package solution;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;


/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {

    /**
     * 拼图构造函数
     */
    public Solution() {
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
    	this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);
        this.currentJNode = null;
        
        Queue<JigsawNode> openList = new ArrayDeque<JigsawNode>();  // 用以保存邻接的节点
        Set<JigsawNode> closeList = new HashSet<JigsawNode>();    // 用以保存已访问的节点
        boolean complete = false;
        int searchedNodesNum = 0;
        //将起始节点放入openList中
        openList.add(beginJNode);
        //如果openList为空,则搜索失败，问题无解;否则循环直到求解成功
        while (!openList.isEmpty()) {
        	// (2-1)取出openList的第一个节点N，置为当前节点currentJNode
            //      若currentJNode为目标节点，则搜索成功，计算解路径，退出
        	this.currentJNode = openList.poll();
        	if (currentJNode.equals(endJNode)) {
        		getPath();
        		complete = true;
        		break;
        	}
        	
        	//从open列表中删除节点N，放入close列表中。
        	closeList.add(currentJNode);
        	searchedNodesNum++;
        	
        	//将所有与v邻接且未曾被访问的节点放入open列表中。
        	JigsawNode[] nextNodes = new JigsawNode[]{
        		new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode),
        		new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode)
            };

            // (2-2)寻找所有与currentJNode邻接且未曾被访问的节点，将它们插入openList中
        	for (int i = 0; i < 4; i++) {
        		if (nextNodes[i].move(i) && !closeList.contains(nextNodes[i])) {
                    openList.add(nextNodes[i]);
                }
            }
        }
        System.out.println("Jigsaw BF Search Result:");
        System.out.println("Begin state:" + this.getBeginJNode().toString());
        System.out.println("End state:" + this.getEndJNode().toString());
        System.out.println("Solution Path: ");
        System.out.println(this.getSolutionPath());
        System.out.println("Total number of searched nodes:" + searchedNodesNum);
        System.out.println("Depth of the current node is:" + this.getCurrentJNode().getNodeDepth());
        return complete;
    }


    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int s1 = 0; // 后续节点不正确的数码个数
        int dimension = JigsawNode.getDimension();
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s1++;
            }
        }
        
        int s2 = 0;// 所有 放错位的数码 个数
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] != endJNode.getNodesState()[index]) {
                s2++;
            }
        }
        
        int s3 = 0;//所有 放错位的数码与其正确位置的距离 之和
        int col0, row0, col1, row1;
        //曼哈顿距离
        for (int index = 1; index <= dimension * dimension; index++) {
        	if (jNode.getNodesState()[index] != 0 && jNode.getNodesState()[index] != endJNode.getNodesState()[index]) {
        		row0 = (int) (index - 1) / dimension;
        		col0 = (int) (index + 4) % dimension;
        		for (int j = 0; j <= dimension * dimension; j++) {
        			if (jNode.getNodesState()[index] == endJNode.getNodesState()[j]) {
        				row1 = (int) (j - 1) / dimension;
        				col1 = (int) (j + 4) % dimension;
        				s3 += (Math.abs(row1 - row0) + Math.abs(col1 - col0));
        				break;
        			}
        		}
        	}
        }
        
        int s4 = 0;//所有 放错位的数码与其正确位置的距离 之和
        //几何距离
        for (int index = 1; index <= dimension * dimension; index++) {
        	if (jNode.getNodesState()[index] != 0 && jNode.getNodesState()[index] != endJNode.getNodesState()[index]) {
        		row0 = (int) (index - 1) / dimension;
        		col0 = (int) (index + 4) % dimension;
        		for (int j = 0; j <= dimension * dimension; j++) {
        			if (jNode.getNodesState()[index] == endJNode.getNodesState()[j]) {
        				row1 = (int) (j - 1) / dimension;
        				col1 = (int) (j + 4) % dimension;
        				s4 += Math.sqrt((row1 - row0) * (row1 - row0)+ (col1 - col0) * (col1 - col0));
        				break;
        			}
        		}
        	}
        }
        
        jNode.setEstimatedValue(7 * s1 + 3 * s2 + 7 * s3 + 10 * s4);
    }
}
