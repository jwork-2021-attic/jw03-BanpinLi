#### 1、写下对代码工作原理的理解
首先是产生处理过的包含.class文件信息的图片，首先在本地载入一个.java源文件，然后使用代码将这个源文件进行编译生成.class字节码文件；然后从本地读入一张图片，将图片作为参数传递并同时创建SteganographyEncoder的对象，这个对象用于后续的编码和解码；然后调用生成对象的编码方法，并将前面生成的.class字节码文件作为参数进行传递；最终生成带有.class字节码文件信息的图片。
然后载入.class到JVM中。先创建SteganographyClassLoader类的一个对象，然后我们给出写入了.class文件的图片的url，作为创建对象的参数；在创建了自定义类加载器之后，调用loadClass方法开始加载类。
loadClass方法加载类的过程。SteganographyClassLoader类实现了ClassLoader接口，作为自定义类加载器用来加载.class文件，在创建这个类的时候，同时会调用父类的构造方法，创建自定义类加载器的父类加载器，用于后续委派机制的实现；在loadClass方法中首先会检测是否有这样一个类已经加载到了JVM中，如果有的话就会使用这个类，而不会继续加载外部的类；当然在这里是不能够检测到的，那么就会向上委派，委派给父类加载器，逐层委派直到最上层的类加载器；然后又从最上层的加载器开始，寻找对应位置是否有相应的.class文件，如果找到了就将其加载到JVM中，同时结束加载。以其中一种情况为例，当我们的.class文件放到了classpath文件夹下时，就会在AppClassLoader加载这一层被检测到，然后加载到JVM中，就完全不会调用自定义类加载器中重写的的findClass方法，所以即使对于图片的解码是错误的，也能够正常运行并进行正常排序，因为根本不会使用到解码后所得到的byte数组。
回到加载过程中，当自上而下的加载器都无法在对应目录找到目标文件的时候，就会调用自定义类加载器中的findClass方法，在这个方法中，首先会根据前面的创建对象时所使用的图片url来读入这个图片，然后创建SteganographyEncoder的对象，并将图片作为参数传递，调用对象的解码方法，将图片所包含的.class文件信息进行还原，并使用加载器中的defineClass方法生成对应的Class对象，结束加载。


#### 2、在W02中实现了两个排序算法

###### 将快速排序编码进图片中：

![](../example.QuickSort.png)

###### 将选择排序编码进图片中：

![](../example.SelectSort.png)


#### 3、使用两张图片分别赋予老头排序功能

###### 赋予快速排序，排序动画如下

[![asciicast](https://asciinema.org/a/436507.svg)](https://asciinema.org/a/98npQ3l0X8FT7CtDnmlUd3GES)


###### 赋予选择排序，排序动画如下

[![asciicast](https://asciinema.org/a/436507.svg)](https://asciinema.org/a/F69d6h3tHsSbFpZhRaZ44JAVt)

#### 4、使用其他同学的图片
    使用的是刘睿哲同学所提供的图片，图片放在了上一级文件夹中，即example.ShellSorter.png

![](../example.ShellSorter.png)

    将图片加载进来能够正常运行，并能够进行正常排序