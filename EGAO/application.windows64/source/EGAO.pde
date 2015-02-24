//ビデオライブラリのインポート
import processing.video.*;
Capture video;//インスタンス生成
//PFont font;//フォントを用意

int w=1280;//画面幅
int h=980;//画面高
int tolerance=15;//色許容値用の変数（後で調整可）
color targetColor=color(255,0,0);//物体の色用の変数（後で変更可）
color targetColor2=color(255,0,0);
color targetColor3=color(255,0,0);
color targetColor4=color(255,0,0);
color targetColor5=color(255,0,0);

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
//色の変更のON/OFFの為に用意（今のところ３色まで）
boolean color1 = false; 
boolean color2 = false;
boolean color3 = false;
boolean color4 = false;
boolean color5 = false;

int x;//図形座標用変数
int y;
int xmin=w,xmax=0;//左端X座標、右端X座標
int ymin=h,ymax=0;//上端Y座標、下端Y座標

int x2;//図形座標用変数2
int y2;
int x2min=w,x2max=0;//左端X座標、右端X座標
int y2min=h,y2max=0;//上端Y座標、下端Y座標

int x3;//図形座標用変数3
int y3;
int x3min=w,x3max=0;//左端X座標、右端X座標
int y3min=h,y3max=0;//上端Y座標、下端Y座標

int x4;//図形座標用変数4
int y4;
int x4min=w,x4max=0;//左端X座標、右端X座標
int y4min=h,y4max=0;//上端Y座標、下端Y座標

int x5;//図形座標用変数5
int y5;
int x5min=w,x5max=0;//左端X座標、右端X座標
int y5min=h,y5max=0;//上端Y座標、下端Y座標

boolean detection=false;//物体検知のフラグ
boolean detection2=false;//物体検知のフラグ2
boolean detection3=false;//物体検知のフラグ3
boolean detection4=false;//物体検知のフラグ4
boolean detection5=false;//物体検知のフラグ5


void setup(){
  size(w, h);//画面サイズ設定
  smooth();//滑らかな描画に設定
  video = new Capture(this, w, h);//キャプチャ映像の設定
  //font=loadFont("Monaco-10.vlw");//フォントをロード
  //textFont(font);//フォントの設定
  noStroke();//外形線なし
  video.start();
  frameRate(60);
  
}

void draw() {
    
        detection=false;//物体検知のフラグをfalse（検知なし）にしておく
        detection2=false;//物体検知のフラグ2をfalse（検知なし）にしておく
        detection3=false;//物体検知のフラグ3をfalse（検知なし）にしておく
        detection4=false;//物体検知のフラグ4をfalse（検知なし）にしておく
        detection5=false;//物体検知のフラグ5をfalse（検知なし）にしておく
        
      //color1の部分
        for(int i=0;i<w*h;i++){//画面全体のピクセル数だけ繰り返し処理
          //物体の色と各ピクセルの色の差を求める（RGB３色分）
          float difRed=abs(red(targetColor)-red(video.pixels[i]));
          float difGreen=abs(green(targetColor)-green(video.pixels[i]));
          float difBlue=abs(blue(targetColor)-blue(video.pixels[i]));

          //RGB各色が許容値以内の場合（近似色である場合）
          if(difRed<tolerance && difGreen<tolerance && difBlue<tolerance){
            //フラグを物体検知有りにする
            detection=true;

            //左端、右端のX座標、上端、下端のY座標を導く
            //今回の値と今までの値を比較し、最小値、最大値を調べる
            xmin=min(i%w,xmin);//左端（X最小値）
            xmax=max(i%w,xmax);//右端（X最大値）
            ymin=min(i/w,ymin);//上端（Y最小値）
            ymax=max(i/w,ymax);//下端（Y最大値）
          }
        }
      //color2の部分
         for(int i=0;i<w*h;i++){//画面全体のピクセル数だけ繰り返し処理
          //物体の色と各ピクセルの色の差を求める（RGB３色分）
          float difRed=abs(red(targetColor2)-red(video.pixels[i]));
          float difGreen=abs(green(targetColor2)-green(video.pixels[i]));
          float difBlue=abs(blue(targetColor2)-blue(video.pixels[i]));

          //RGB各色が許容値以内の場合（近似色である場合）
          if(difRed<tolerance && difGreen<tolerance && difBlue<tolerance){
            //フラグを物体検知有りにする
            detection2=true;

            //左端、右端のX座標、上端、下端のY座標を導く
            //今回の値と今までの値を比較し、最小値、最大値を調べる
            x2min=min(i%w,x2min);//左端（X最小値）
            x2max=max(i%w,x2max);//右端（X最大値）
            y2min=min(i/w,y2min);//上端（Y最小値）
            y2max=max(i/w,y2max);//下端（Y最大値）
          }
        }
        //color3の部分
         for(int i=0;i<w*h;i++){//画面全体のピクセル数だけ繰り返し処理
          //物体の色と各ピクセルの色の差を求める（RGB３色分）
          float difRed=abs(red(targetColor3)-red(video.pixels[i]));
          float difGreen=abs(green(targetColor3)-green(video.pixels[i]));
          float difBlue=abs(blue(targetColor3)-blue(video.pixels[i]));

          //RGB各色が許容値以内の場合（近似色である場合）
          if(difRed<tolerance && difGreen<tolerance && difBlue<tolerance){
            //フラグを物体検知有りにする
            detection3=true;

            //左端、右端のX座標、上端、下端のY座標を導く
            //今回の値と今までの値を比較し、最小値、最大値を調べる
            x3min=min(i%w,x3min);//左端（X最小値）
            x3max=max(i%w,x3max);//右端（X最大値）
            y3min=min(i/w,y3min);//上端（Y最小値）
            y3max=max(i/w,y3max);//下端（Y最大値）
          }
        }    
        //color4の部分
        for(int i=0;i<w*h;i++){//画面全体のピクセル数だけ繰り返し処理
          //物体の色と各ピクセルの色の差を求める（RGB３色分）
          float difRed=abs(red(targetColor4)-red(video.pixels[i]));
          float difGreen=abs(green(targetColor4)-green(video.pixels[i]));
          float difBlue=abs(blue(targetColor4)-blue(video.pixels[i]));

          //RGB各色が許容値以内の場合（近似色である場合）
          if(difRed<tolerance && difGreen<tolerance && difBlue<tolerance){
            //フラグを物体検知有りにする
            detection4=true;

            //左端、右端のX座標、上端、下端のY座標を導く
            //今回の値と今までの値を比較し、最小値、最大値を調べる
            x4min=min(i%w,x4min);//左端（X最小値）
            x4max=max(i%w,x4max);//右端（X最大値）
            y4min=min(i/w,y4min);//上端（Y最小値）
            y4max=max(i/w,y4max);//下端（Y最大値）
          }
        }
 //color5の部分
         for(int i=0;i<w*h;i++){//画面全体のピクセル数だけ繰り返し処理
          //物体の色と各ピクセルの色の差を求める（RGB３色分）
          float difRed=abs(red(targetColor5)-red(video.pixels[i]));
          float difGreen=abs(green(targetColor5)-green(video.pixels[i]));
          float difBlue=abs(blue(targetColor5)-blue(video.pixels[i]));

          //RGB各色が許容値以内の場合（近似色である場合）
          if(difRed<tolerance && difGreen<tolerance && difBlue<tolerance){
            //フラグを物体検知有りにする
            detection5=true;

            //左端、右端のX座標、上端、下端のY座標を導く
            //今回の値と今までの値を比較し、最小値、最大値を調べる
            x5min=min(i%w,x5min);//左端（X最小値）
            x5max=max(i%w,x5max);//右端（X最大値）
            y5min=min(i/w,y5min);//上端（Y最小値）
            y5max=max(i/w,y5max);//下端（Y最大値）
          }
        }
    if(detection==true){//物体検知有りの場合
      
      x=(xmin+xmax)/2;//X座標を左端と右端の座標の中点とする
      y=(ymin+ymax)/2;//Y座標を上端と下端の座標の中点とする
      //左端、右端、上端、下端の座標値を初期化しておく
      xmin=w;
      xmax=0;
      ymin=h;
      ymax=0;
      
      pointX[var]=x;
      pointY[var]=y;
      
      var++;
      
      System.out.println("x: xの座標"+x  +"  yの座標"+y); //現在の座標を表示
    }
    if(detection2==true){//物体検知有りの場合
      x2=(x2min+x2max)/2;//X座標を左端と右端の座標の中点とする
      y2=(y2min+y2max)/2;//Y座標を上端と下端の座標の中点とする
      //左端、右端、上端、下端の座標値を初期化しておく
      x2min=w;
      x2max=0;
      y2min=h;
      y2max=0;
      
      point2X[var2]=x2;
      point2Y[var2]=y2;
      
      var2++;
      
      System.out.println("x2: xの座標"+x2  +"  yの座標"+y2); //現在の座標を表示
    }
    if(detection3==true){//物体検知有りの場合
      x3=(x3min+x3max)/2;//X座標を左端と右端の座標の中点とする
      y3=(y3min+y3max)/2;//Y座標を上端と下端の座標の中点とする
      //左端、右端、上端、下端の座標値を初期化しておく
      x3min=w;
      x3max=0;
      y3min=h;
      y3max=0;
    
      point3X[var3]=x3;
      point3Y[var3]=y3;
      
      var3++;
      
      System.out.println("x3: xの座標"+x3  +"  yの座標"+y3); //現在の座標を表示
    }
    if(detection4==true){//物体検知有りの場合
      x4=(x4min+x4max)/2;//X座標を左端と右端の座標の中点とする
      y4=(y4min+y4max)/2;//Y座標を上端と下端の座標の中点とする
      //左端、右端、上端、下端の座標値を初期化しておく
      x4min=w;
      x4max=0;
      y4min=h;
      y4max=0;
      
      point4X[var4]=x4;
      point4Y[var4]=y4;
      
      var4++;
      
      System.out.println("x4: xの座標"+x4  +"  yの座標"+y4); //現在の座標を表示
    }
    if(detection5==true){//物体検知有りの場合 
      x5=(x5min+x5max)/2;//X座標を左端と右端の座標の中点とする
      y5=(y5min+y5max)/2;//Y座標を上端と下端の座標の中点とする
      //左端、右端、上端、下端の座標値を初期化しておく
      x5min=w;
      x5max=0;
      y5min=h;
      y5max=0;
      
      point5X[var5]=x5;
      point5Y[var5]=y5;
      
      var5++;
      
      System.out.println("x5: xの座標"+x5  +"  yの座標"+y5); //現在の座標を表示
    }
     
 // if(count!=0){ //起動時に初期設定の色が設定されており勝手に描画されないように、クリックされてから描画する。
 //最初に色を指定した後、中点に色を置いてしまうので、そこを今後対応。
        for(int i=0;i<var -1;i++) { //描画処理
            if(color1==true){
              fill(targetColor);//塗り色
              ellipse(w-pointX[i], pointY[i], 40, 40);
            }
        }
        for(int i=0;i<=var2 -1;i++) { //描画処理
           
            if(color2==true){ 
              fill(targetColor2);//塗り色
              ellipse(w-point2X[i], point2Y[i], 40, 40);            }
        }
        for(int i=0;i<=var3 -1;i++) { //描画処理
            if(color3==true){
              fill(targetColor3);//塗り色
              ellipse(w-point3X[i], point3Y[i], 40, 40);
            }
        }
        for(int i=0;i<=var4 -1;i++) { //描画処理
          if(color4==true){
              fill(targetColor4);//塗り色
              ellipse(w-point4X[i], point4Y[i], 40, 40);
            }
        }
        for(int i=0;i<=var5 -1;i++) { //描画処理
          if(color5==true){
              fill(targetColor5);//塗り色
              ellipse(w-point5X[i], point5Y[i], 40, 40);
            }  
        }
        
     
      
  
  //以下は設定内容の表示
  String s;//物体検知有無表示の文字列変数
  if((detection==true)||(detection2==true)||(detection3==true)||(detection4==true)||(detection5==true)){//物体検知有りの場合
    s="detected";//表示：「検知」
  }else{
    s="none";//表示：「なし」
  }
  text(tolerance+": "+s,20,10); //文字列表示（許容値：物体検知有無）
  scale(-1.0,1.0);
  tint(255,60);  
  image(video,-w,0);
}


void mousePressed(){//クリックしたら
  //マウス座標上のピクセルの色（物体の色）を記憶しておく
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

void keyPressed(){
  if(key=='c'){//「c」キーを押した場合
     for(int i=0; i<= var-1; i++) {
         pointX[i]=0;pointY[i]=0;
    }
  }
  if(key==CODED){
    if(keyCode==LEFT){//左キーを押した場合
      tolerance-=1;   //許容値を-1する
    }
    if(keyCode==RIGHT){//右キーを押した場合
      tolerance+=1;    //許容値を+1する
    }
  }
}

void captureEvent(Capture video) {
  video.read();
}
