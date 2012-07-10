package util;

public class ArrayP {
	public static String getArray1(int[] a)
	{
		String str="{";
		for(int i=0;i<a.length;i++){
			str+=a[i];
			if(i!=a.length-1)
			{
				str+=",";
			}
		}
		str+="}";
		return str;
	}
	
	public static String getArray2(int[][] a)
	{
		String str="{";
		for(int i=0;i<a.length;i++){
			str+=getArray1(a[i]);
			if(i!=a.length-1)
			{
				str+=",";
			}
		str+="\n";
		}
		str+="}";
		return str;
	}
}
