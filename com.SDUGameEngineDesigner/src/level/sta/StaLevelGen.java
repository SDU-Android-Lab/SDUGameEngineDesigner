package level.sta;

import util.IGenerator;

public class StaLevelGen implements IGenerator
{
  protected static String nl;
  public static synchronized StaLevelGen create(String lineSeparator)
  {
    nl = lineSeparator;
    StaLevelGen result = new StaLevelGen();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "level.sta#";
  protected final String TEXT_2 = "#" + NL + "" + NL + "public class ";
  protected final String TEXT_3 = " extends org.sdu.android.map.painter.level.StaticLevel{" + NL + "\tpublic ";
  protected final String TEXT_4 = "(){" + NL + "\t\tsuper.initialize();" + NL + "\t\t";
  protected final String TEXT_5 = "add(new ";
  protected final String TEXT_6 = "());" + NL + "\t\t";
  protected final String TEXT_7 = NL + "\t}" + NL + "}";

	/* (non-javadoc)
	* @see IGenerator#generate(Object)
	*/
	public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     LevelBase  level=(LevelBase)argument;
    stringBuffer.append(TEXT_1);
    stringBuffer.append(level.getName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(level.getName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(level.getName() );
    stringBuffer.append(TEXT_4);
     for (String str:level.getList()){
		
    stringBuffer.append(TEXT_5);
    stringBuffer.append(str);
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}