# Read Me

- **文件结构**
- **实现方法**

-------------------

- **文件结构**
	**MazeBug.java**：
属性| 介绍
-------|----------
`boolean isEnd` | 保存是否到达终点
`Stack<Location> crossLocation` | 通过将Location入出栈来实现深搜
`Integer stepCount = 0` | 记录已走过的步数
`boolean hasShown = false` | 结果信息是否显示过
`Boolean[][] visited` | 记录Location是否被访问过
`int probability[]` | 保存每个方向上走的概率
`void increasePro(int direction)` | 增加指定方向上的概率
`void decreasePro(int direction)` | 减少指定方向上的概率
`MazeBug()` | 构造函数
`void act()` | 执行step时调用
`ArrayList<Location> getValid(Location loc)` | 找到所有可以访问的Location
`boolean canMove()` | 虫子是否可以移动
`void move()` | 往前移动
`void backTracking()` | 回溯
`Location getLocationByPro(ArrayList<Location> locations)` | 根据概率来选要访问的Location

- **实现方法**
实际上就是实现深搜，我理解给出的MazeBug中栈中存储ArrayList是为了直接存储路径，可以通过它来得到Location是否被访问过，但我觉得这样太浪费空间，因此采用存储Location的方式来对栈进行处理，另外定义一个visited数组来存Location是否被访问过。

**act():**在act被调用时，首先初始化，即判断步数是否为0，然后判断是否到达终点，最后才是判断是否可以move，可以移动时，调用move函数，步数增加；不可以移动时，出栈，回溯，此方向的上的概率减1，步数加1

**getValid():**在此函数判断是否到达终点，即从该位置是否可以一步到达终点。没有到达终点的话找所有未被访问过的Location

**canMove():**通过调用getValid()即可实现

**move**通过getValid()和getLocationByPro()函数来得到要移动过去的Location，设置方向，移动，对应的visited设置为true，压栈，并且该方向概率加1

**backTracking()**设置方向并返回到上一步的Location

**getLocationByPro()**利用随机数和概率来选要移动到的位置，有0.6的概率移动到概率最大的方向上的Location，其他方向上的Location平分0.4的概率