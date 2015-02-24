import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.video.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class EGAO extends PApplet {

//\u30d3\u30c7\u30aa\u30e9\u30a4\u30d6\u30e9\u30ea\u306e\u30a4\u30f3\u30dd\u30fc\u30c8

Capture video;//\u30a4\u30f3\u30b9\u30bf\u30f3\u30b9\u751f\u6210
//PFont font;//\u30d5\u30a9\u30f3\u30c8\u3092\u7528\u610f

int w=640;//\u753b\u9762\u5e45
int h=480;//\u753b\u9762\u9ad8
int tolerance=15;//\u8272\u8a31\u5bb9\u5024\u7528\u306e\u5909\u6570\uff08\u5f8c\u3067\u8abf\u6574\u53ef\uff09
int targetColor=color(255,0,0);//\u7269\u4f53\u306e\u8272\u7528\u306e\u5909\u6570\uff08\u5f8c\u3067\u5909\u66f4\u53ef\uff09
int targetColor2=color(255,0,0);
int targetColor3=color(255,0,0);
int targetColor4=color(255,0,0);
int targetColor5=color(255,0,0);

int[] pointX = new int[w*h];
int[] pointY = new int[w*h];
int[] point2X = new int[w*h];
int[] point2Y = new int[w*h];
int[] point3X = new int[w*h];
int[] point3Y = new int[w*h];
int[] point4X = new int[w*h];
int[] point4Y = new int[w*h];
int[] point5X = new int[w*h];
int[] point5Y = new int[w*h];

int var=0;
int var2=0;
int var3=0;
int var4=0;
int var5=0;

int count=0;
//\u8272\u306e\u5909\u66f4\u306eON/OFF\u306e\u70ba\u306b\u7528\u610f\uff08\u4eca\u306e\u3068\u3053\u308d\uff13\u8272\u307e\u3067\uff09
boolean color1 = false; 
boolean color2 = false;
boolean color3 = false;
boolean color4 = false;
boolean color5 = false;

int x;//\u56f3\u5f62\u5ea7\u6a19\u7528\u5909\u6570
int y;
int xmin=w,xmax=0;//\u5de6\u7aefX\u5ea7\u6a19\u3001\u53f3\u7aefX\u5ea7\u6a19
int ymin=h,ymax=0;//\u4e0a\u7aefY\u5ea7\u6a19\u3001\u4e0b\u7aefY\u5ea7\u6a19

int x2;//\u56f3\u5f62\u5ea7\u6a19\u7528\u5909\u65702
int y2;
int x2min=w,x2max=0;//\u5de6\u7aefX\u5ea7\u6a19\u3001\u53f3\u7aefX\u5ea7\u6a19
int y2min=h,y2max=0;//\u4e0a\u7aefY\u5ea7\u6a19\u3001\u4e0b\u7aefY\u5ea7\u6a19

int x3;//\u56f3\u5f62\u5ea7\u6a19\u7528\u5909\u65703
int y3;
int x3min=w,x3max=0;//\u5de6\u7aefX\u5ea7\u6a19\u3001\u53f3\u7aefX\u5ea7\u6a19
int y3min=h,y3max=0;//\u4e0a\u7aefY\u5ea7\u6a19\u3001\u4e0b\u7aefY\u5ea7\u6a19

int x4;//\u56f3\u5f62\u5ea7\u6a19\u7528\u5909\u65704
int y4;
int x4min=w,x4max=0;//\u5de6\u7aefX\u5ea7\u6a19\u3001\u53f3\u7aefX\u5ea7\u6a19
int y4min=h,y4max=0;//\u4e0a\u7aefY\u5ea7\u6a19\u3001\u4e0b\u7aefY\u5ea7\u6a19

int x5;//\u56f3\u5f62\u5ea7\u6a19\u7528\u5909\u65705
int y5;
int x5min=w,x5max=0;//\u5de6\u7aefX\u5ea7\u6a19\u3001\u53f3\u7aefX\u5ea7\u6a19
int y5min=h,y5max=0;//\u4e0a\u7aefY\u5ea7\u6a19\u3001\u4e0b\u7aefY\u5ea7\u6a19

boolean detection=false;//\u7269\u4f53\u691c\u77e5\u306e\u30d5\u30e9\u30b0
boolean detection2=false;//\u7269\u4f53\u691c\u77e5\u306e\u30d5\u30e9\u30b02
boolean detection3=false;//\u7269\u4f53\u691c\u77e5\u306e\u30d5\u30e9\u30b03
boolean detection4=false;//\u7269\u4f53\u691c\u77e5\u306e\u30d5\u30e9\u30b04
boolean detection5=false;//\u7269\u4f53\u691c\u77e5\u306e\u30d5\u30e9\u30b05


public void setup(){
  size(w, h);//\u753b\u9762\u30b5\u30a4\u30ba\u8a2d\u5b9a
  smooth();//\u6ed1\u3089\u304b\u306a\u63cf\u753b\u306b\u8a2d\u5b9a
  video = new Capture(this, w, h);//\u30ad\u30e3\u30d7\u30c1\u30e3\u6620\u50cf\u306e\u8a2d\u5b9a
  //font=loadFont("Monaco-10.vlw");//\u30d5\u30a9\u30f3\u30c8\u3092\u30ed\u30fc\u30c9
  //textFont(font);//\u30d5\u30a9\u30f3\u30c8\u306e\u8a2d\u5b9a
  noStroke();//\u5916\u5f62\u7dda\u306a\u3057
  video.start();
  frameRate(60);
  
}

public void draw() {
    
        detection=false;//\u7269\u4f53\u691c\u77e5\u306e\u30d5\u30e9\u30b0\u3092false\uff08\u691c\u77e5\u306a\u3057\uff09\u306b\u3057\u3066\u304a\u304f
        detection2=false;//\u7269\u4f53\u691c\u77e5\u306e\u30d5\u30e9\u30b02\u3092false\uff08\u691c\u77e5\u306a\u3057\uff09\u306b\u3057\u3066\u304a\u304f
        detection3=false;//\u7269\u4f53\u691c\u77e5\u306e\u30d5\u30e9\u30b03\u3092false\uff08\u691c\u77e5\u306a\u3057\uff09\u306b\u3057\u3066\u304a\u304f
        detection4=false;//\u7269\u4f53\u691c\u77e5\u306e\u30d5\u30e9\u30b04\u3092false\uff08\u691c\u77e5\u306a\u3057\uff09\u306b\u3057\u3066\u304a\u304f
        detection5=false;//\u7269\u4f53\u691c\u77e5\u306e\u30d5\u30e9\u30b05\u3092false\uff08\u691c\u77e5\u306a\u3057\uff09\u306b\u3057\u3066\u304a\u304f
        
      //color1\u306e\u90e8\u5206
        for(int i=0;i<w*h;i++){//\u753b\u9762\u5168\u4f53\u306e\u30d4\u30af\u30bb\u30eb\u6570\u3060\u3051\u7e70\u308a\u8fd4\u3057\u51e6\u7406
          //\u7269\u4f53\u306e\u8272\u3068\u5404\u30d4\u30af\u30bb\u30eb\u306e\u8272\u306e\u5dee\u3092\u6c42\u3081\u308b\uff08RGB\uff13\u8272\u5206\uff09
          float difRed=abs(red(targetColor)-red(video.pixels[i]));
          float difGreen=abs(green(targetColor)-green(video.pixels[i]));
          float difBlue=abs(blue(targetColor)-blue(video.pixels[i]));

          //RGB\u5404\u8272\u304c\u8a31\u5bb9\u5024\u4ee5\u5185\u306e\u5834\u5408\uff08\u8fd1\u4f3c\u8272\u3067\u3042\u308b\u5834\u5408\uff09
          if(difRed<tolerance && difGreen<tolerance && difBlue<tolerance){
            //\u30d5\u30e9\u30b0\u3092\u7269\u4f53\u691c\u77e5\u6709\u308a\u306b\u3059\u308b
            detection=true;

            //\u5de6\u7aef\u3001\u53f3\u7aef\u306eX\u5ea7\u6a19\u3001\u4e0a\u7aef\u3001\u4e0b\u7aef\u306eY\u5ea7\u6a19\u3092\u5c0e\u304f
            //\u4eca\u56de\u306e\u5024\u3068\u4eca\u307e\u3067\u306e\u5024\u3092\u6bd4\u8f03\u3057\u3001\u6700\u5c0f\u5024\u3001\u6700\u5927\u5024\u3092\u8abf\u3079\u308b
            xmin=min(i%w,xmin);//\u5de6\u7aef\uff08X\u6700\u5c0f\u5024\uff09
            xmax=max(i%w,xmax);//\u53f3\u7aef\uff08X\u6700\u5927\u5024\uff09
            ymin=min(i/w,ymin);//\u4e0a\u7aef\uff08Y\u6700\u5c0f\u5024\uff09
            ymax=max(i/w,ymax);//\u4e0b\u7aef\uff08Y\u6700\u5927\u5024\uff09
          }
        }
      //color2\u306e\u90e8\u5206
         for(int i=0;i<w*h;i++){//\u753b\u9762\u5168\u4f53\u306e\u30d4\u30af\u30bb\u30eb\u6570\u3060\u3051\u7e70\u308a\u8fd4\u3057\u51e6\u7406
          //\u7269\u4f53\u306e\u8272\u3068\u5404\u30d4\u30af\u30bb\u30eb\u306e\u8272\u306e\u5dee\u3092\u6c42\u3081\u308b\uff08RGB\uff13\u8272\u5206\uff09
          float difRed=abs(red(targetColor2)-red(video.pixels[i]));
          float difGreen=abs(green(targetColor2)-green(video.pixels[i]));
          float difBlue=abs(blue(targetColor2)-blue(video.pixels[i]));

          //RGB\u5404\u8272\u304c\u8a31\u5bb9\u5024\u4ee5\u5185\u306e\u5834\u5408\uff08\u8fd1\u4f3c\u8272\u3067\u3042\u308b\u5834\u5408\uff09
          if(difRed<tolerance && difGreen<tolerance && difBlue<tolerance){
            //\u30d5\u30e9\u30b0\u3092\u7269\u4f53\u691c\u77e5\u6709\u308a\u306b\u3059\u308b
            detection2=true;

            //\u5de6\u7aef\u3001\u53f3\u7aef\u306eX\u5ea7\u6a19\u3001\u4e0a\u7aef\u3001\u4e0b\u7aef\u306eY\u5ea7\u6a19\u3092\u5c0e\u304f
            //\u4eca\u56de\u306e\u5024\u3068\u4eca\u307e\u3067\u306e\u5024\u3092\u6bd4\u8f03\u3057\u3001\u6700\u5c0f\u5024\u3001\u6700\u5927\u5024\u3092\u8abf\u3079\u308b
            x2min=min(i%w,x2min);//\u5de6\u7aef\uff08X\u6700\u5c0f\u5024\uff09
            x2max=max(i%w,x2max);//\u53f3\u7aef\uff08X\u6700\u5927\u5024\uff09
            y2min=min(i/w,y2min);//\u4e0a\u7aef\uff08Y\u6700\u5c0f\u5024\uff09
            y2max=max(i/w,y2max);//\u4e0b\u7aef\uff08Y\u6700\u5927\u5024\uff09
          }
        }
        //color3\u306e\u90e8\u5206
         for(int i=0;i<w*h;i++){//\u753b\u9762\u5168\u4f53\u306e\u30d4\u30af\u30bb\u30eb\u6570\u3060\u3051\u7e70\u308a\u8fd4\u3057\u51e6\u7406
          //\u7269\u4f53\u306e\u8272\u3068\u5404\u30d4\u30af\u30bb\u30eb\u306e\u8272\u306e\u5dee\u3092\u6c42\u3081\u308b\uff08RGB\uff13\u8272\u5206\uff09
          float difRed=abs(red(targetColor3)-red(video.pixels[i]));
          float difGreen=abs(green(targetColor3)-green(video.pixels[i]));
          float difBlue=abs(blue(targetColor3)-blue(video.pixels[i]));

          //RGB\u5404\u8272\u304c\u8a31\u5bb9\u5024\u4ee5\u5185\u306e\u5834\u5408\uff08\u8fd1\u4f3c\u8272\u3067\u3042\u308b\u5834\u5408\uff09
          if(difRed<tolerance && difGreen<tolerance && difBlue<tolerance){
            //\u30d5\u30e9\u30b0\u3092\u7269\u4f53\u691c\u77e5\u6709\u308a\u306b\u3059\u308b
            detection3=true;

            //\u5de6\u7aef\u3001\u53f3\u7aef\u306eX\u5ea7\u6a19\u3001\u4e0a\u7aef\u3001\u4e0b\u7aef\u306eY\u5ea7\u6a19\u3092\u5c0e\u304f
            //\u4eca\u56de\u306e\u5024\u3068\u4eca\u307e\u3067\u306e\u5024\u3092\u6bd4\u8f03\u3057\u3001\u6700\u5c0f\u5024\u3001\u6700\u5927\u5024\u3092\u8abf\u3079\u308b
            x3min=min(i%w,x3min);//\u5de6\u7aef\uff08X\u6700\u5c0f\u5024\uff09
            x3max=max(i%w,x3max);//\u53f3\u7aef\uff08X\u6700\u5927\u5024\uff09
            y3min=min(i/w,y3min);//\u4e0a\u7aef\uff08Y\u6700\u5c0f\u5024\uff09
            y3max=max(i/w,y3max);//\u4e0b\u7aef\uff08Y\u6700\u5927\u5024\uff09
          }
        }    
        //color4\u306e\u90e8\u5206
        for(int i=0;i<w*h;i++){//\u753b\u9762\u5168\u4f53\u306e\u30d4\u30af\u30bb\u30eb\u6570\u3060\u3051\u7e70\u308a\u8fd4\u3057\u51e6\u7406
          //\u7269\u4f53\u306e\u8272\u3068\u5404\u30d4\u30af\u30bb\u30eb\u306e\u8272\u306e\u5dee\u3092\u6c42\u3081\u308b\uff08RGB\uff13\u8272\u5206\uff09
          float difRed=abs(red(targetColor4)-red(video.pixels[i]));
          float difGreen=abs(green(targetColor4)-green(video.pixels[i]));
          float difBlue=abs(blue(targetColor4)-blue(video.pixels[i]));

          //RGB\u5404\u8272\u304c\u8a31\u5bb9\u5024\u4ee5\u5185\u306e\u5834\u5408\uff08\u8fd1\u4f3c\u8272\u3067\u3042\u308b\u5834\u5408\uff09
          if(difRed<tolerance && difGreen<tolerance && difBlue<tolerance){
            //\u30d5\u30e9\u30b0\u3092\u7269\u4f53\u691c\u77e5\u6709\u308a\u306b\u3059\u308b
            detection4=true;

            //\u5de6\u7aef\u3001\u53f3\u7aef\u306eX\u5ea7\u6a19\u3001\u4e0a\u7aef\u3001\u4e0b\u7aef\u306eY\u5ea7\u6a19\u3092\u5c0e\u304f
            //\u4eca\u56de\u306e\u5024\u3068\u4eca\u307e\u3067\u306e\u5024\u3092\u6bd4\u8f03\u3057\u3001\u6700\u5c0f\u5024\u3001\u6700\u5927\u5024\u3092\u8abf\u3079\u308b
            x4min=min(i%w,x4min);//\u5de6\u7aef\uff08X\u6700\u5c0f\u5024\uff09
            x4max=max(i%w,x4max);//\u53f3\u7aef\uff08X\u6700\u5927\u5024\uff09
            y4min=min(i/w,y4min);//\u4e0a\u7aef\uff08Y\u6700\u5c0f\u5024\uff09
            y4max=max(i/w,y4max);//\u4e0b\u7aef\uff08Y\u6700\u5927\u5024\uff09
          }
        }
 //color5\u306e\u90e8\u5206
         for(int i=0;i<w*h;i++){//\u753b\u9762\u5168\u4f53\u306e\u30d4\u30af\u30bb\u30eb\u6570\u3060\u3051\u7e70\u308a\u8fd4\u3057\u51e6\u7406
          //\u7269\u4f53\u306e\u8272\u3068\u5404\u30d4\u30af\u30bb\u30eb\u306e\u8272\u306e\u5dee\u3092\u6c42\u3081\u308b\uff08RGB\uff13\u8272\u5206\uff09
          float difRed=abs(red(targetColor5)-red(video.pixels[i]));
          float difGreen=abs(green(targetColor5)-green(video.pixels[i]));
          float difBlue=abs(blue(targetColor5)-blue(video.pixels[i]));

          //RGB\u5404\u8272\u304c\u8a31\u5bb9\u5024\u4ee5\u5185\u306e\u5834\u5408\uff08\u8fd1\u4f3c\u8272\u3067\u3042\u308b\u5834\u5408\uff09
          if(difRed<tolerance && difGreen<tolerance && difBlue<tolerance){
            //\u30d5\u30e9\u30b0\u3092\u7269\u4f53\u691c\u77e5\u6709\u308a\u306b\u3059\u308b
            detection5=true;

            //\u5de6\u7aef\u3001\u53f3\u7aef\u306eX\u5ea7\u6a19\u3001\u4e0a\u7aef\u3001\u4e0b\u7aef\u306eY\u5ea7\u6a19\u3092\u5c0e\u304f
            //\u4eca\u56de\u306e\u5024\u3068\u4eca\u307e\u3067\u306e\u5024\u3092\u6bd4\u8f03\u3057\u3001\u6700\u5c0f\u5024\u3001\u6700\u5927\u5024\u3092\u8abf\u3079\u308b
            x5min=min(i%w,x5min);//\u5de6\u7aef\uff08X\u6700\u5c0f\u5024\uff09
            x5max=max(i%w,x5max);//\u53f3\u7aef\uff08X\u6700\u5927\u5024\uff09
            y5min=min(i/w,y5min);//\u4e0a\u7aef\uff08Y\u6700\u5c0f\u5024\uff09
            y5max=max(i/w,y5max);//\u4e0b\u7aef\uff08Y\u6700\u5927\u5024\uff09
          }
        }
    if(detection==true){//\u7269\u4f53\u691c\u77e5\u6709\u308a\u306e\u5834\u5408
      
      x=(xmin+xmax)/2;//X\u5ea7\u6a19\u3092\u5de6\u7aef\u3068\u53f3\u7aef\u306e\u5ea7\u6a19\u306e\u4e2d\u70b9\u3068\u3059\u308b
      y=(ymin+ymax)/2;//Y\u5ea7\u6a19\u3092\u4e0a\u7aef\u3068\u4e0b\u7aef\u306e\u5ea7\u6a19\u306e\u4e2d\u70b9\u3068\u3059\u308b
      //\u5de6\u7aef\u3001\u53f3\u7aef\u3001\u4e0a\u7aef\u3001\u4e0b\u7aef\u306e\u5ea7\u6a19\u5024\u3092\u521d\u671f\u5316\u3057\u3066\u304a\u304f
      xmin=w;
      xmax=0;
      ymin=h;
      ymax=0;
      
      pointX[var]=x;
      pointY[var]=y;
      
      var++;
      
      System.out.println("x: x\u306e\u5ea7\u6a19"+x  +"  y\u306e\u5ea7\u6a19"+y); //\u73fe\u5728\u306e\u5ea7\u6a19\u3092\u8868\u793a
    }
    if(detection2==true){//\u7269\u4f53\u691c\u77e5\u6709\u308a\u306e\u5834\u5408
      x2=(x2min+x2max)/2;//X\u5ea7\u6a19\u3092\u5de6\u7aef\u3068\u53f3\u7aef\u306e\u5ea7\u6a19\u306e\u4e2d\u70b9\u3068\u3059\u308b
      y2=(y2min+y2max)/2;//Y\u5ea7\u6a19\u3092\u4e0a\u7aef\u3068\u4e0b\u7aef\u306e\u5ea7\u6a19\u306e\u4e2d\u70b9\u3068\u3059\u308b
      //\u5de6\u7aef\u3001\u53f3\u7aef\u3001\u4e0a\u7aef\u3001\u4e0b\u7aef\u306e\u5ea7\u6a19\u5024\u3092\u521d\u671f\u5316\u3057\u3066\u304a\u304f
      x2min=w;
      x2max=0;
      y2min=h;
      y2max=0;
      
      point2X[var2]=x2;
      point2Y[var2]=y2;
      
      var2++;
      
      System.out.println("x2: x\u306e\u5ea7\u6a19"+x2  +"  y\u306e\u5ea7\u6a19"+y2); //\u73fe\u5728\u306e\u5ea7\u6a19\u3092\u8868\u793a
    }
    if(detection3==true){//\u7269\u4f53\u691c\u77e5\u6709\u308a\u306e\u5834\u5408
      x3=(x3min+x3max)/2;//X\u5ea7\u6a19\u3092\u5de6\u7aef\u3068\u53f3\u7aef\u306e\u5ea7\u6a19\u306e\u4e2d\u70b9\u3068\u3059\u308b
      y3=(y3min+y3max)/2;//Y\u5ea7\u6a19\u3092\u4e0a\u7aef\u3068\u4e0b\u7aef\u306e\u5ea7\u6a19\u306e\u4e2d\u70b9\u3068\u3059\u308b
      //\u5de6\u7aef\u3001\u53f3\u7aef\u3001\u4e0a\u7aef\u3001\u4e0b\u7aef\u306e\u5ea7\u6a19\u5024\u3092\u521d\u671f\u5316\u3057\u3066\u304a\u304f
      x3min=w;
      x3max=0;
      y3min=h;
      y3max=0;
    
      point3X[var3]=x3;
      point3Y[var3]=y3;
      
      var3++;
      
      System.out.println("x3: x\u306e\u5ea7\u6a19"+x3  +"  y\u306e\u5ea7\u6a19"+y3); //\u73fe\u5728\u306e\u5ea7\u6a19\u3092\u8868\u793a
    }
    if(detection4==true){//\u7269\u4f53\u691c\u77e5\u6709\u308a\u306e\u5834\u5408
      x4=(x4min+x4max)/2;//X\u5ea7\u6a19\u3092\u5de6\u7aef\u3068\u53f3\u7aef\u306e\u5ea7\u6a19\u306e\u4e2d\u70b9\u3068\u3059\u308b
      y4=(y4min+y4max)/2;//Y\u5ea7\u6a19\u3092\u4e0a\u7aef\u3068\u4e0b\u7aef\u306e\u5ea7\u6a19\u306e\u4e2d\u70b9\u3068\u3059\u308b
      //\u5de6\u7aef\u3001\u53f3\u7aef\u3001\u4e0a\u7aef\u3001\u4e0b\u7aef\u306e\u5ea7\u6a19\u5024\u3092\u521d\u671f\u5316\u3057\u3066\u304a\u304f
      x4min=w;
      x4max=0;
      y4min=h;
      y4max=0;
      
      point4X[var4]=x4;
      point4Y[var4]=y4;
      
      var4++;
      
      System.out.println("x4: x\u306e\u5ea7\u6a19"+x4  +"  y\u306e\u5ea7\u6a19"+y4); //\u73fe\u5728\u306e\u5ea7\u6a19\u3092\u8868\u793a
    }
    if(detection5==true){//\u7269\u4f53\u691c\u77e5\u6709\u308a\u306e\u5834\u5408 
      x5=(x5min+x5max)/2;//X\u5ea7\u6a19\u3092\u5de6\u7aef\u3068\u53f3\u7aef\u306e\u5ea7\u6a19\u306e\u4e2d\u70b9\u3068\u3059\u308b
      y5=(y5min+y5max)/2;//Y\u5ea7\u6a19\u3092\u4e0a\u7aef\u3068\u4e0b\u7aef\u306e\u5ea7\u6a19\u306e\u4e2d\u70b9\u3068\u3059\u308b
      //\u5de6\u7aef\u3001\u53f3\u7aef\u3001\u4e0a\u7aef\u3001\u4e0b\u7aef\u306e\u5ea7\u6a19\u5024\u3092\u521d\u671f\u5316\u3057\u3066\u304a\u304f
      x5min=w;
      x5max=0;
      y5min=h;
      y5max=0;
      
      point5X[var5]=x5;
      point5Y[var5]=y5;
      
      var5++;
      
      System.out.println("x5: x\u306e\u5ea7\u6a19"+x5  +"  y\u306e\u5ea7\u6a19"+y5); //\u73fe\u5728\u306e\u5ea7\u6a19\u3092\u8868\u793a
    }
     
 // if(count!=0){ //\u8d77\u52d5\u6642\u306b\u521d\u671f\u8a2d\u5b9a\u306e\u8272\u304c\u8a2d\u5b9a\u3055\u308c\u3066\u304a\u308a\u52dd\u624b\u306b\u63cf\u753b\u3055\u308c\u306a\u3044\u3088\u3046\u306b\u3001\u30af\u30ea\u30c3\u30af\u3055\u308c\u3066\u304b\u3089\u63cf\u753b\u3059\u308b\u3002
 //\u6700\u521d\u306b\u8272\u3092\u6307\u5b9a\u3057\u305f\u5f8c\u3001\u4e2d\u70b9\u306b\u8272\u3092\u7f6e\u3044\u3066\u3057\u307e\u3046\u306e\u3067\u3001\u305d\u3053\u3092\u4eca\u5f8c\u5bfe\u5fdc\u3002
        for(int i=0;i<var -1;i++) { //\u63cf\u753b\u51e6\u7406
            if(color1==true){
              fill(targetColor);//\u5857\u308a\u8272
              ellipse(w-pointX[i], pointY[i], 40, 40);
            }
        }
        for(int i=0;i<=var2 -1;i++) { //\u63cf\u753b\u51e6\u7406
           
            if(color2==true){ 
              fill(targetColor2);//\u5857\u308a\u8272
              ellipse(w-point2X[i], point2Y[i], 40, 40);            }
        }
        for(int i=0;i<=var3 -1;i++) { //\u63cf\u753b\u51e6\u7406
            if(color3==true){
              fill(targetColor3);//\u5857\u308a\u8272
              ellipse(w-point3X[i], point3Y[i], 40, 40);
            }
        }
        for(int i=0;i<=var4 -1;i++) { //\u63cf\u753b\u51e6\u7406
          if(color4==true){
              fill(targetColor4);//\u5857\u308a\u8272
              ellipse(w-point4X[i], point4Y[i], 40, 40);
            }
        }
        for(int i=0;i<=var5 -1;i++) { //\u63cf\u753b\u51e6\u7406
          if(color5==true){
              fill(targetColor5);//\u5857\u308a\u8272
              ellipse(w-point5X[i], point5Y[i], 40, 40);
            }  
        }
        
     
      
  
  //\u4ee5\u4e0b\u306f\u8a2d\u5b9a\u5185\u5bb9\u306e\u8868\u793a
  String s;//\u7269\u4f53\u691c\u77e5\u6709\u7121\u8868\u793a\u306e\u6587\u5b57\u5217\u5909\u6570
  if((detection==true)||(detection2==true)||(detection3==true)||(detection4==true)||(detection5==true)){//\u7269\u4f53\u691c\u77e5\u6709\u308a\u306e\u5834\u5408
    s="detected";//\u8868\u793a\uff1a\u300c\u691c\u77e5\u300d
  }else{
    s="none";//\u8868\u793a\uff1a\u300c\u306a\u3057\u300d
  }
  text(tolerance+": "+s,20,10); //\u6587\u5b57\u5217\u8868\u793a\uff08\u8a31\u5bb9\u5024\uff1a\u7269\u4f53\u691c\u77e5\u6709\u7121\uff09
  scale(-1.0f,1.0f);
  tint(255,60);  
  image(video,-w,0);
}


public void mousePressed(){//\u30af\u30ea\u30c3\u30af\u3057\u305f\u3089
  //\u30de\u30a6\u30b9\u5ea7\u6a19\u4e0a\u306e\u30d4\u30af\u30bb\u30eb\u306e\u8272\uff08\u7269\u4f53\u306e\u8272\uff09\u3092\u8a18\u61b6\u3057\u3066\u304a\u304f
  if(count==0){
    targetColor=video.pixels[w-mouseX+mouseY*w];
    color1=true;
  }
  if(count==1){
    targetColor2=video.pixels[w-mouseX+mouseY*w];
    color2=true;
  }
  if(count==2){
    targetColor3=video.pixels[w-mouseX+mouseY*w];  
    color3=true;
  }
    if(count==3){
    targetColor4=video.pixels[w-mouseX+mouseY*w];  
    color4=true;
  }
    if(count==4){
    targetColor5=video.pixels[w-mouseX+mouseY*w];  
    color5=true;
  }

  count++;
}

public void keyPressed(){
  if(key=='c'){//\u300cc\u300d\u30ad\u30fc\u3092\u62bc\u3057\u305f\u5834\u5408
     for(int i=0; i<= var-1; i++) {
         pointX[i]=0;pointY[i]=0;
    }
  }
  if(key==CODED){
    if(keyCode==LEFT){//\u5de6\u30ad\u30fc\u3092\u62bc\u3057\u305f\u5834\u5408
      tolerance-=1;   //\u8a31\u5bb9\u5024\u3092-1\u3059\u308b
    }
    if(keyCode==RIGHT){//\u53f3\u30ad\u30fc\u3092\u62bc\u3057\u305f\u5834\u5408
      tolerance+=1;    //\u8a31\u5bb9\u5024\u3092+1\u3059\u308b
    }
  }
}

public void captureEvent(Capture video) {
  video.read();
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "EGAO" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
