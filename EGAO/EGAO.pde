import processing.video.*;

Capture camera;
color targetColor=color(0,0,0);
int w = 640;
int h = 480;
int x=0;
int y=0;
boolean detection=false;//物体検知用の変数
boolean clicked=false;
int tolerance=15;//許容値
int[] pointX = new int[w*h];
int[] pointY = new int[w*h];
int var = 0;
int i;
int xmin=w,xmax=0;//左端X座標、右端X座標
int ymin=h,ymax=0;//上端Y座標、下端Y座標

void setup(){
  //画面サイズは数字で入力
  size(640,480);
  smooth();

  String[] cameras = Capture.list();//利用可能な動画入力の配列を取得

  if(cameras.length == 0){
    println("There are no cameras available for capture");
    exit();
  }else{
    for(int i = 0;i<cameras.length;i++){
      println(cameras[i]);
    }
  }
  camera = new Capture(this,cameras[0]);
  camera.start();
}

void draw(){
  if(camera.available()== true){
    camera.read();
  }else{return;}
  scale(-1,1);//画面を反転
  image(camera,-w,0);
  detection=false;

  for(int i=0;i<w*h;i++){
    //red()関数でpixelsのRGBのred要素だけを取りすために差を引いている。green(),blue()も同様
    float differenceRed = abs(red(targetColor)-red(camera.pixels[i]));
    float differenceGreen = abs(green(targetColor)-green(camera.pixels[i]));
    float differenceBlue = abs(blue(targetColor)-blue(camera.pixels[i]));

    if(differenceRed<tolerance && differenceGreen<tolerance && differenceBlue<tolerance){
      //フラグを物体検知有りにする
      detection=true;
      //左端、右端のX座標、上端、下端のY座標を導く
      //今回の値と今までの値を比較し、最小値、最大値を調べる
      xmin=min(i%w,xmax);//左端（X最小値）
      xmax=max(i%w,xmin);//右端（X最大値）
      ymin=min(i/w,ymin);//上端（Y最小値）
      ymax=max(i/w,ymax);//下端（Y最大値）
    }
  }
   if(detection==true){//物体検知有りの場合
      x=((xmin+xmax)/2)-w;//X座標を左端と右端の座標の中点とする(ミラー処理の為wを引いている)
      y=(ymin+ymax)/2;//Y座標を上端と下端の座標の中点とする
      //左端、右端、上端、下端の座標値を初期化しておく
      xmin=w;
      xmax=0;
      ymin=h;
      ymax=0;
      pointX[var]=x;
      pointY[var]=y;
      if(clicked==true){
        var++;
      }

      //System.out.println("x: xの座標"+x  +"  yの座標"+y); //現在の座標を表示
   }
   if(clicked==true){
     for(i=0;i<var;i++){
        fill(targetColor);
        noStroke();
        ellipse(pointX[i],pointY[i],25,25);
     }
   }
   fill(255);
   scale(-1,1);//文字が逆になってしまう為
   text("許容値="+tolerance,20,20);
}

void mousePressed(){
  loadPixels();
  //画面サイズに合わせる為に下のようにする
  targetColor=camera.pixels[-mouseX+mouseY*w];
  clicked=true;
  updatePixels();
}

void keyPressed(){
  if(key == 'c'){
    var=0;
  }else if(keyCode == RIGHT){
    tolerance++;
  }else if(keyCode == LEFT){
    tolerance--;
  }
}
