package com.orange.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.EGLConfig;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.view.View;

public class CubeUnderLightRenderer implements Renderer {
    public static View makeCubeView(Context  context)
    {
        GLSurfaceView GLView = new GLSurfaceView(context); //创建一个GLSurfaceView
        GLView.setRenderer(new CubeUnderLightRenderer());
        return GLView;
    }
    float box[] = new float[] {
                    // FRONT
                    -0.5f, -0.5f,  0.5f,
                     0.5f, -0.5f,  0.5f,
                    -0.5f,  0.5f,  0.5f,
                     0.5f,  0.5f,  0.5f,
                    // BACK
                    -0.5f, -0.5f, -0.5f,
                    -0.5f,  0.5f, -0.5f,
                     0.5f, -0.5f, -0.5f,
                     0.5f,  0.5f, -0.5f,
                    // LEFT
                    -0.5f, -0.5f,  0.5f,
                    -0.5f,  0.5f,  0.5f,
                    -0.5f, -0.5f, -0.5f,
                    -0.5f,  0.5f, -0.5f,
                    // RIGHT
                     0.5f, -0.5f, -0.5f,
                     0.5f,  0.5f, -0.5f,
                     0.5f, -0.5f,  0.5f,
                     0.5f,  0.5f,  0.5f,
                    // TOP
                    -0.5f,  0.5f,  0.5f,
                     0.5f,  0.5f,  0.5f,
                     -0.5f,  0.5f, -0.5f,
                     0.5f,  0.5f, -0.5f,
                    // BOTTOM
                    -0.5f, -0.5f,  0.5f,
                    -0.5f, -0.5f, -0.5f,
                     0.5f, -0.5f,  0.5f,
                     0.5f, -0.5f, -0.5f,
            };
    float lightAmbient[] = new float[] { 0.2f, 0.3f, 0.6f, 1.0f };  //环境光
    float lightDiffuse[] = new float[] { 0.2f, 0.3f, 0.6f, 1.0f };//漫反射光
    float[] lightPos = new float[] {0,0,3,1};  //光源位置
/*
* 因为进行光照处理，你必须告知系统你定义的模型各个面的方向，以便系统计算光影情况，方向的描述是通过向量点来描述的
*/     
    float norms[] = new float[] { //法向量数组，用于描述个顶点的方向，以此说明各个面的方向
                    // FRONT
                    0f,  0f,  1f, //方向为(0,0,0)至(0,0,1)即Z轴正方向
                    0f,  0f,  1f,
                    0f,  0f,  1f,
                    0f,  0f,  1f,
                    // BACK
                    0f,  0f,  -1f,
                    0f,  0f,  -1f,
                    0f,  0f,  -1f,
                    0f,  0f,  -1f,
                    // LEFT
                    -1f,  0f,  0f,
                    -1f,  0f,  0f,
                    -1f,  0f,  0f,
                    -1f,  0f,  0f,
                    // RIGHT
                    1f, 0f, 0f,
                    1f, 0f, 0f,
                    1f, 0f, 0f,
                    1f, 0f, 0f,
                    // TOP
                    0f,  1f, 0f,
                    0f,  1f, 0f,
                    0f,  1f, 0f,
                    0f,  1f, 0f,
                    // BOTTOM
                    0f,  -1f, 0f,
                    0f,  -1f, 0f,
                    0f,  -1f, 0f,
                    0f,  -1f, 0f
            };

    
    FloatBuffer cubeBuff;
    FloatBuffer normBuff;
    
    float xrot = 0.0f;
    float yrot = 0.0f;
    
    /**
     * 将float数组转换存储在字节缓冲数组
     * @param arr
     * @return
     */
    public FloatBuffer makeFloatBuffer(float[] arr) {
            ByteBuffer bb = ByteBuffer.allocateDirect(arr.length * 4);//分配缓冲空间，一个float占4个字节
            bb.order(ByteOrder.nativeOrder()); //设置字节顺序， 其中ByteOrder.nativeOrder()是获取本机字节顺序
            FloatBuffer fb = bb.asFloatBuffer(); //转换为float型
            fb.put(arr);        //添加数据
            fb.position(0);      //设置数组的起始位置
            return fb;
    }
    
    public CubeUnderLightRenderer() {
            // TODO Auto-generated constructor stub
            cubeBuff = makeFloatBuffer(box);//转换float数组
            normBuff = makeFloatBuffer(norms);
    }
    
    
    protected void init(GL10 gl) {
            gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);//设置清屏时背景的颜色，R，G，B，A
            
            gl.glEnable(GL10.GL_LIGHTING); //启用光照
            gl.glEnable(GL10.GL_LIGHT0);  //开启光源0,最多可以开启8个光源
            //设置光照参数，也可以使用默认的，不设置
            gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient,     0);
            gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuse,     0);
            gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0);
            
            gl.glNormalPointer(GL10.GL_FLOAT, 0, normBuff);
            gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
            
            gl.glEnable(GL10.GL_DEPTH_TEST); //启用深度缓存
            gl.glEnable(GL10.GL_CULL_FACE);  //启用背面剪裁
            gl.glClearDepthf(1.0f);    // 设置深度缓存值
            gl.glDepthFunc(GL10.GL_LEQUAL);  // 设置深度缓存比较函数，GL_LEQUAL表示新的像素的深度缓存值小于等于当前像素的深度缓存值（通过gl.glClearDepthf(1.0f)设置）时通过深度测试   
            gl.glShadeModel(GL10.GL_SMOOTH);// 设置阴影模式GL_SMOOTH
    }
    
    
    @Override
    public void onSurfaceChanged(GL10 gl, int w, int h) {
            // TODO Auto-generated method stub
            gl.glViewport(0, 0, w, h); //设置视窗
            gl.glMatrixMode(GL10.GL_PROJECTION); // 设置投影矩阵
            gl.glLoadIdentity();  //设置矩阵为单位矩阵，相当于重置矩阵               
            GLU.gluPerspective(gl, 45.0f, ((float) w) / h, 0.1f, 10f);//设置透视范围      
    }
    
    @Override
    public void onDrawFrame(GL10 gl) {
            // TODO Auto-generated method stub
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);// 清除屏幕和深度缓存
            
            gl.glMatrixMode(GL10.GL_MODELVIEW);   //切换至模型观察矩阵
            gl.glLoadIdentity();// 重置当前的模型观察矩阵
            GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1, 0);//设置视点和模型中心位置
    
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeBuff);//设置顶点数据
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    
            gl.glRotatef(xrot, 1, 0, 0);  //绕着(0,0,0)与(1,0,0)即x轴旋转
            gl.glRotatef(yrot, 0, 1, 0);
            
            gl.glColor4f(1.0f, 0, 0, 1.0f);   //设置颜色，红色
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);  //绘制正方型FRONT面
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
    
            gl.glColor4f(0, 1.0f, 0, 1.0f);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
            
            gl.glColor4f(0, 0, 1.0f, 1.0f);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
    
            xrot += 0.5f;
            yrot += 0.5f;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, javax.microedition.khronos.egl.EGLConfig config)
    {
        // TODO Auto-generated method stub
        init(gl);
    }

}
