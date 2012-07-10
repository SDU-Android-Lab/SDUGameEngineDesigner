package scene;

import util.IGenerator;
import util.StaGroup;
import util.GroupCollision;
import util.GetName;
import java.util.List;

public class SceneGen implements IGenerator
{
  protected static String nl;
  public static synchronized SceneGen create(String lineSeparator)
  {
    nl = lineSeparator;
    SceneGen result = new SceneGen();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "scene#";
  protected final String TEXT_2 = "#" + NL + "public class ";
  protected final String TEXT_3 = " extends org.sdu.android.map.painter.scene.Scene" + NL + "implements org.sdu.android.map.action.CollisionEventListener," + NL + "org.sdu.android.map.action.MapEventListener{" + NL;
  protected final String TEXT_4 = NL + "\t";
  protected final String TEXT_5 = " ";
  protected final String TEXT_6 = ";";
  protected final String TEXT_7 = NL + "\torg.sdu.android.map.action.CollisionGroup ";
  protected final String TEXT_8 = ";";
  protected final String TEXT_9 = NL + "\tandroid.graphics.Point ";
  protected final String TEXT_10 = ";";
  protected final String TEXT_11 = NL + "public void initialize(){" + NL + "" + NL + "org.sdu.android.map.painter.ground.SimpleMap map=new ";
  protected final String TEXT_12 = "();" + NL + "this.setMap((org.sdu.android.map.painter.ground.SimpleMap)map);" + NL + "" + NL + "org.sdu.android.map.painter.level.DynamicLevel dynlevel=new org.sdu.android.map.painter.level.DynamicLevel();" + NL + "dynlevel.initialize();";
  protected final String TEXT_13 = NL + "\t\t\t";
  protected final String TEXT_14 = "=new ";
  protected final String TEXT_15 = "();" + NL + "\t\t\tdynlevel.add(";
  protected final String TEXT_16 = ");" + NL + "\t";
  protected final String TEXT_17 = NL + "this.setDynLevel(dynlevel);" + NL;
  protected final String TEXT_18 = NL + "  ";
  protected final String TEXT_19 = "=new org.sdu.android.map.action.CollisionGroup(";
  protected final String TEXT_20 = ",";
  protected final String TEXT_21 = ");" + NL + "  this.addCollisionGroup(";
  protected final String TEXT_22 = ");" + NL + "\t";
  protected final String TEXT_23 = NL + "  ";
  protected final String TEXT_24 = NL + "  \t\t\t";
  protected final String TEXT_25 = NL + "  \t\t\t";
  protected final String TEXT_26 = "=new android.graphics.Point(";
  protected final String TEXT_27 = ",";
  protected final String TEXT_28 = ");" + NL + "\t\t\tmap.add(";
  protected final String TEXT_29 = ");" + NL + "\t";
  protected final String TEXT_30 = NL + "  setHero((org.sdu.android.sprite.Hero)";
  protected final String TEXT_31 = ");" + NL + "\t    getHero().setMap(map);" + NL + "\t    " + NL + "\t  " + NL + "\t\tthis.addCollisionEventListener(this);" + NL + "\t\tthis.setMel(this);" + NL + "}" + NL + "" + NL + "\tpublic void collisionOccured(org.sdu.android.map.action.CollisionEvent event) {" + NL + "\t\t";
  protected final String TEXT_32 = NL + "\t\t";
  protected final String TEXT_33 = NL + "\t\torg.sdu.android.map.action.CollisionGroup group=event.getGroup();" + NL + "\t\t";
  protected final String TEXT_34 = NL + "\t\t";
  protected final String TEXT_35 = "else ";
  protected final String TEXT_36 = NL + "\t\t\tif (group.equals(";
  protected final String TEXT_37 = ")){" + NL + "\t\t\t\t//handle ";
  protected final String TEXT_38 = NL + "\t\t\t}" + NL + "\t\t";
  protected final String TEXT_39 = NL + "\t}" + NL + "\t" + NL + "\tpublic void mapEventOccured(org.sdu.android.map.action.MapEvent event){\t" + NL + "\t\t";
  protected final String TEXT_40 = NL + "\t\t";
  protected final String TEXT_41 = NL + "\t\tandroid.graphics.Point point=event.getPoint();" + NL + "\t\t";
  protected final String TEXT_42 = NL + "\t\t";
  protected final String TEXT_43 = "else ";
  protected final String TEXT_44 = "if (point.equals(";
  protected final String TEXT_45 = ")){" + NL + "\t\t\t\t//handle ";
  protected final String TEXT_46 = NL + "\t\t\t}" + NL + "\t\t";
  protected final String TEXT_47 = NL + "\t}" + NL + "}" + NL;
  protected final String TEXT_48 = NL;

	/* (non-javadoc)
	* @see IGenerator#generate(Object)
	*/
	public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     SceneBase  scene=(SceneBase)argument; GetName getName=new GetName();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(scene.getName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(scene.getName());
    stringBuffer.append(TEXT_3);
    for (String className: scene.getDynResource()){
    stringBuffer.append(TEXT_4);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(getName.newName(className));
    stringBuffer.append(TEXT_6);
    }
     for (int i=0;i<scene.getCollision().size();i++){
    stringBuffer.append(TEXT_7);
    stringBuffer.append(getName.newName("org.sdu.android.map.action.CollisionGroup"));
    stringBuffer.append(TEXT_8);
    }
     for (int i=0;i<scene.getMapEvent().size();i++){
    stringBuffer.append(TEXT_9);
    stringBuffer.append(getName.newName("android.graphics.Point"));
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    stringBuffer.append(scene.getMap());
    stringBuffer.append(TEXT_12);
     	for (String className:scene.getDynResource()){
    stringBuffer.append(TEXT_13);
    stringBuffer.append(getName.getFirstName(className));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(getName.getFirstName(className));
    stringBuffer.append(TEXT_16);
    	}
    stringBuffer.append(TEXT_17);
     int count=0; for (GroupCollision group:scene.getCollision()){
    String cname=getName.getNameAt("org.sdu.android.map.action.CollisionGroup",count++);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cname );
    stringBuffer.append(TEXT_19);
    stringBuffer.append(getName.getFirstName(group.getSprite1()));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(getName.getFirstName(group.getSprite2()));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cname );
    stringBuffer.append(TEXT_22);
    	}
    stringBuffer.append(TEXT_23);
     count=0;	for (StaGroup group:scene.getMapEvent()){
    stringBuffer.append(TEXT_24);
    String cname=getName.getNameAt("android.graphics.Point",count++);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cname );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(group.getPoint().x);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(group.getPoint().y);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cname );
    stringBuffer.append(TEXT_29);
    	}
    stringBuffer.append(TEXT_30);
    stringBuffer.append(getName.getFirstName(scene.getHero()));
    stringBuffer.append(TEXT_31);
    List<GroupCollision> clist=scene.getCollision();
    stringBuffer.append(TEXT_32);
     if (!clist.isEmpty()) {
    stringBuffer.append(TEXT_33);
    count=0; for (int i=0;i<clist.size();i++){ 
    stringBuffer.append(TEXT_34);
    if (count>0) {
    stringBuffer.append(TEXT_35);
    }
    stringBuffer.append(TEXT_36);
    stringBuffer.append(getName.getNameAt("org.sdu.android.map.action.CollisionGroup",count));
    stringBuffer.append(TEXT_37);
    stringBuffer.append(clist.get(count++).getSEvent());
    stringBuffer.append(TEXT_38);
    } 
		}
    stringBuffer.append(TEXT_39);
    List<StaGroup> slist=scene.getMapEvent();
    stringBuffer.append(TEXT_40);
     if (!slist.isEmpty()) {
    stringBuffer.append(TEXT_41);
    count=0; for (int i=0;i<slist.size();i++){ 
    stringBuffer.append(TEXT_42);
    if (count>0) {
    stringBuffer.append(TEXT_43);
    }
    stringBuffer.append(TEXT_44);
    stringBuffer.append(getName.getNameAt("android.graphics.Point",count));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(slist.get(count++).getEvent());
    stringBuffer.append(TEXT_46);
    } 
		}
    stringBuffer.append(TEXT_47);
    stringBuffer.append(TEXT_48);
    return stringBuffer.toString();
  }
}