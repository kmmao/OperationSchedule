**本软件开发相关资料：** 高速铁路调度指挥仿真实验教程  

**开发人员：** 谭老师，喻斗  

**开发语言：** java  

**软件：** eclipse（需要安装javafx插件，方便mvc模式各层分离）

**jdk版本：** 1.8    

**消息队列组件：** rabbit mq  

**前端组件：** javafx  

**测试系统：** 中标麒麟

---  
**工作描述：**  

谭：*******  

喻：列车运行调度模块设计（参考实验教程实验一和实验二）  
1. 菜单  
    - 系统登录  
    - 参数设置  
    - 绘制运行符号（重）  
        列车运行线：
        区间封锁  
        区间慢行  
        车站封锁  
        车站慢行  
        电网检修  
        图表记载  
        区间作业取消绘制
    - 阶段计划（重）  
        批量（分界口接入）计划  
        图定计划  
        删除运行线  
        恢复删除  
        批量删除计划线  
        恢复批量删除  
        删除全部计划停运线  
        下达计划  
        定时下达计划设置  
        申请邻台计划  
        批量选择计划线  
        批量移动计划线  
        按间隔分隔批量线条  
        更改通过/到开  
    - 列车管理  
    - 显示选项
    - 培训方案管理  
    - 帮助  
2. 主画布
    - 行 面板  
        当前工作运行图，用于铺画列车运行计划并进行阶段计划的调整  
    - 日 面板  
        查询日班计划在本台的落成情况  
    - 施 面板  
        查看本台施工计划信息，包括相应的施工计划以及施工调度台提前拟写的调度命令  
3. 工具栏
    - 状态栏  
        显示时间，选中车次信息等  
    - 车次搜索  
    - 触  
        改变车站进路触发方式  
    - 转实际  
        将列车运行线从开始站到选中的车站的运行点转换为实际点  
    - 下计划  
        下达阶段计划到所管辖的车站  
    - 发送本台阶段计划到邻台  
    - 接收邻台计划  
        邻台计划发送到本台后，本台会有机器提示声，点击本按钮可接收计划  
    - 开始手工画线  
    - 结束手工画线  
    - 放大运行图  
    - 缩小运行图  
    - 显示上行运行线  
    - 显示下行运行线  
    - 鼠标收点  
        手工将列车的计划线上的点转为实际  
    - 显示正晚点信息  
        显示已转实际的列车的正晚点信息  
    - 显示本台基本运行图  
    - 显示乘务机车叫班信息  
    - 显示区间运行时间分析  
    - 显示车底接续情况  
    - 显示运行线上机车信息  
    - 显示运行线的交出车次  
4. 鼠标右键菜单  
    - 按点选中  
    - 按线选中  
5. 交班存图

---  
使用的mvc的界面设计模，model是后台数据结构，后缀为Controller的是控制器，文件夹中以Controller后缀为区别的两个文件分别是视图和控制器，后续开发的逻辑重点再Controller中。  

src下中Main是主结构，见结构.png  

station是重点结构，操作大表的滑动结构见大表.png  

test是后续测试包，

**后续阶段需要解决的问题：**
1. 数据结构（重）
2. 主画布模块的布局测试
    - 滑动，折叠
    - 线条的托动，点的托动
    - 是否有跨UI元素的移动，比如从panel1移动到panel2
3. UI主线程的异步测试，使用多线程，确保界面响应的及时
    - 网络延时测试
    - 大量绘制线条可能会出现卡顿
    - 网络I/O过大时卡顿
    - 本地I/O过大时卡顿




