package cn.edu.shu.cs.android.experiment01.debug_quicksort;

import android.util.Log;

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
     //��С�ڵ���point��Ԫ���Ƶ��������
     //������point��Ԫ���Ƶ��ұ�����
     int index=p;
     for (int i = index; i < r+1; ++i) {
            if (a[i]-point <= 0) {
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
      //ȷ����ֵ㣬��������Ԫ�ؽ����ƶ�
      //���ǿ��������㷨�Ĺؼ�����
      int q=partition(a,p,r);
      //����������
      qsort(a,p,q-1);
      //���Ұ������
      qsort(a,q+1,r);
     }
    }
    
  public void StartQs() {
    	//����һ����
    	
    	int len=10;
    	int a[]=new int[len];
    	//��ʼ��a����

    	Log.i(QuickSort.Tag, "ԭʼ�������£�");
    	for(int i=0;i< a.length;i++)
    	{
    	   //����a.length�������
    	a[i] = (int)(Math.random()*100000);  
    	Log.i(QuickSort.Tag, Integer.toString(a[i]));
    	}
    	Log.i(QuickSort.Tag, "---------------------");
    	Log.i(QuickSort.Tag, "��һ�η����");
    	partition(a,0,len-1);
    	for(int i=0;i< a.length;i++)
    	{
    		Log.i(QuickSort.Tag, Integer.toString(a[i]));
    	}
    	Log.i(QuickSort.Tag, "---------------------");
    	//��������
    	qsort(a, 0, len-1);



    	Log.i(QuickSort.Tag, "�������������£�");
    	for(int i=0;i< a.length;i++)
    	{
    		Log.i(QuickSort.Tag, Integer.toString(a[i]));
    	}


    	}
    

}
