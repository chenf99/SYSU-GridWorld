# Read Me

- **文件结构**
- **实现的功能**
- **实现方法**

-------------------

- **文件结构**
	**ImplementImageIO.java**
	
		实现imagereader包中的IImageIO接口，读取操作不允许使用API，写入操作可使用API
	**ImplementImageProcessor.java**

		实现imagereader包中的IImageProcessor接口，可使用API
	
	**ImageProcessorTest.java**
	
		用于junit测试，查看生成的图片是否与目标图片相同


- **实现的功能**

```
public Image myRead(String filePath)
```
实现读取文件操作，通过BufferedInputStream来读取byte数组，根据位图数据存储的特点，第18~21字节保存位图宽度，第22~25字节保存位图高度，第34~37保存位图数据大小，读取完前54个字节后继续读取到的就是位图数据，像素就可以从这里读取。
```
public Image myWrite(Image image, String filePath)
```
实现生成图片操作，可以利用Java API `ImageIO.write(RenderedImage im,String formatName,File output)`， 但首先要将Image转成BufferedImage。

```
public Image showChanelR(Image sourceImage)
```
提取并显示彩色图像的红色通道，采用Java API `Toolkit.getDefaultToolkit().createImage(ImageProducer producer)`来实现。ImageProducer用FilteredImageSource，同时自己写FIlter

```
public Image showGray(Image sourceImage)
```
把读取的彩色图像转换成灰色图像

```
public void testHeightAndWidth()
```
测试高度和宽度是否相同

```
public void testPixel()
```
测试像素值是否相同

- **实现方法**
读取图片中除了按照位图存储的特点读取byte数组外，还要将byte数组转为int，如：`int width = (int)((info[4] & 0xFF) 
				| ((info[5] & 0xFF)<<8) 
				| ((info[6] & 0xFF)<<16) 
				| ((info[7] & 0xFF)<<24));`
最后还要从像素数组转成Image：

```
image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(
				width, height, pixel, 0, width));
```

生成图片中Image转BufferedImage：

```
BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
```
FIlter的实现：
每个通道实现一个继承RGBImageFilter的类，并且必须实现`public int filterRGB(int x, int y, int rgb)方法`