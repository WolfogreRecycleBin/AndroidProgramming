package com.example.administrator.debug_quicksort;

import android.util.Log;

/**
 * Created by Administrator on 12/08/2015.
 */
public class QuickSort {

    private final static String Tag ="QUICKSORT";

    private void swap(int a[],int i,int j)
    {
        int tmp=a[i];
        a[i]=a[j];
        a[j]=tmp;
    }

    private int partition(int a[],int p,int r)
    {
        int point = a[r];
        //将小于等于point的元素移到左边区域
        //将大于point的元素移到右边区域
        int index=p;
        for (int i = index; i < r+1; ++i) {
            //if (a[i]-point <= 0) {
            //This is the BUG!
            if (a[i] < point) {
                swap(a, index++, i);
            }
        }
        swap(a,index,r);
        return index;
    }

    private void qsort(int a[],int p,int r)
    {
        if(p< r)
        {
            //确定拆分点，并对数组元素进行移动
            //这是快速排序算法的关键步骤
            int q=partition(a,p,r);
            //对左半段排序
            qsort(a,p,q-1);
            //对右半段排序
            qsort(a,q+1,r);
        }
    }

    public void StartQs() {
        //声明一个类

        int len=10;
        int a[]=new int[len];
        //初始化a数组

        Log.i(QuickSort.Tag, "原始数组如下：");
        for(int i=0;i< a.length;i++)
        {
            //产生a.length个随机数
            a[i] = (int)(Math.random()*100000);
            Log.i(QuickSort.Tag, Integer.toString(a[i]));
        }
        Log.i(QuickSort.Tag, "---------------------");
        Log.i(QuickSort.Tag, "第一次分组后");
        partition(a,0,len-1);
        for(int i=0;i< a.length;i++)
        {
            Log.i(QuickSort.Tag, Integer.toString(a[i]));
        }
        Log.i(QuickSort.Tag, "---------------------");
        //快速排序
        qsort(a, 0, len-1);



        Log.i(QuickSort.Tag, "排序后的数组如下：");
        for(int i=0;i< a.length;i++)
        {
            Log.i(QuickSort.Tag, Integer.toString(a[i]));
        }


    }


}
