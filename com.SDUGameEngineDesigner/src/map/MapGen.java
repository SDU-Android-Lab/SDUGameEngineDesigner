package map;

import util.IGenerator;
import util.ArrayP;

public class MapGen implements IGenerator
{
  protected static String nl;
  public static synchronized MapGen create(String lineSeparator)
  {
    nl = lineSeparator;
    MapGen result = new MapGen();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "map#";
  protected final String TEXT_2 = "#" + NL + "public class ";
  protected final String TEXT_3 = " extends org.sdu.android.map.painter.ground.";
  protected final String TEXT_4 = "SimpleMap";
  protected final String TEXT_5 = "SearchableMap";
  protected final String TEXT_6 = "{" + NL + " static int [][]mapint =  ";
  protected final String TEXT_7 = " ;" + NL + "\tpublic ";
  protected final String TEXT_8 = "(){" + NL + "\t\tsuper( mapint,";
  protected final String TEXT_9 = ",";
  protected final String TEXT_10 = ");" + NL + "\t\t";
  protected final String TEXT_11 = "this.setDownLevel(new ";
  protected final String TEXT_12 = "()); ";
  protected final String TEXT_13 = NL + "\t\t";
  protected final String TEXT_14 = "this.setUpLevel(new ";
  protected final String TEXT_15 = "());";
  protected final String TEXT_16 = NL + "\t\t" + NL + "\t}" + NL + "}" + NL;
  protected final String TEXT_17 = NL;

	/* (non-javadoc)
	* @see IGenerator#generate(Object)
	*/
	public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     MapBase  map=(MapBase)argument;
    stringBuffer.append(TEXT_1);
    stringBuffer.append(map.getName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(map.getName());
    stringBuffer.append(TEXT_3);
    if (map.isAutoSearch()){
    stringBuffer.append(TEXT_4);
    }else{
    stringBuffer.append(TEXT_5);
    }
    stringBuffer.append(TEXT_6);
    stringBuffer.append(ArrayP.getArray2(map.getMap()));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(map.getName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(map.getTileWidth());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(map.getTileHeight());
    stringBuffer.append(TEXT_10);
     if(map.hasDownLevel()){ 
    stringBuffer.append(TEXT_11);
    stringBuffer.append(map.getStaDownLevel());
    stringBuffer.append(TEXT_12);
      }
    stringBuffer.append(TEXT_13);
     if(map.hasUpLevel()) { 
    stringBuffer.append(TEXT_14);
    stringBuffer.append( map.getStaUpLevel());
    stringBuffer.append(TEXT_15);
      }
    stringBuffer.append(TEXT_16);
    stringBuffer.append(TEXT_17);
    return stringBuffer.toString();
  }
}