# README  
Some information about my part5 code：  
	  
- **File manifest**  
- **Description**  
- **Configuration instructions**   
- **Operating instructions**  
- **Copyright information**  
  
-------------------
	  
## File manifest  
	  
 - 1    `SparseGridNode.java` 、`SparseBoundedGrid.java` 、`SparseGridRunner.java`、`sonar-project.properties`  
 - 2    `SparseBoundedGrid.java` 、`SparseGridRunner.java`、`sonar-project.properties`  
 - 3    `UnBoundedGrid.java` 、`UnBoundedGridRunner.java`、`sonar-project.properties`  
	   
	
## Description	
	  
In Part5 I implemented 3 kind of Grid, one **SparseGridNode version** of SparseBoundedGrid, one **HashMap version** of SparseBoundedGrid and one **array version** of UnBoundedGrid.    
     
The **SparseBoundedGrid of SparseGridNode version** uses an array of SparseGridNode to store the occupants in the grid.Every row of the grid is a list which contains all the occupants in a column.    
     
The **SparseBoundedGrid of HashMap version** uses a HashMap to store the occupants in the grid.The location of the occupants is the key for the map.  
	  
The **UnBoundedGrid of array version** uses a 16x16 array to store the occupants in the grid.The implementation of this UnBoundedGrid is a bit like the BoundedGrid of an array version.When put an occupant into a location which is outside the current array bounds, the array bounds will double until they are large enough.  	
	  
### Configuration instructions  
	
You need first install Java JDK and get the gridworld.jar to test the codes.The gridworld.jar contains some important classes that I quote in my codes.	
	
###Operating instructions		
		
For testing the grid, you need first to javac the XXXGrid.java, then you can javac the XXXGridRunner.java, then uses java to run it.		
![这里写图片描述](https://img-blog.csdn.net/20180422195011772?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2NoZW5mMTk5OQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)		
Click the world to select the grid that I implemented, then you can change the grid to you selected and test it.		
	
###Copyright information	
All these codes were written by 16340017.