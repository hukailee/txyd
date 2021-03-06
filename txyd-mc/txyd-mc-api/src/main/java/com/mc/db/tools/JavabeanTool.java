package com.mc.db.tools;



import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Administrator on 2016/9/7.
 */
public class JavabeanTool {
//	public static void test() throws Exception {
//		Class<?> tClass=UserEntity.class;
//		BeanInfo info= Introspector.getBeanInfo(tClass);
//		PropertyDescriptor[] props=info.getPropertyDescriptors();
//		Arrays.stream(props).limit(2).forEach(e-> {
//
//			System.out.println(e.getName());
//			System.out.println("\t"+e.getPropertyType().getPackage());
//			System.out.println("\t"+e.getPropertyType().getName());
//			System.out.println("\t"+e.getPropertyType().isAssignableFrom(Date.class));
//			System.out.println("\t"+e.getPropertyType());
//			System.out.println("\t"+e.getReadMethod());
//			System.out.println("\t"+e.getWriteMethod());
//			System.out.println("\t"+e.getWriteMethod().getName());
//		});
//	}
	public static <T,U> void copy(Class<T> sourceClass, Class<U> descClass, Class<? extends Collection> sCollection, Class<? extends Collection> dCollection) throws Exception {
		String method = "\tpublic static {dCollectionFatherClassName}<{descClassName}> copy({sCollectionFatherClassName}<{sourceClassName}> source){\n"
				+"\t\tif(source==null) return null;\n"
				+"\t\tfor({sourceClassName} item : source ){\n"
				+"\t\t\t{descClassName} temp = copy(item);\n "
				+"\t\t\tdesc.add(temp);\n "
				+"\t\t}\n "
				+"\t\treturn desc; \n "
				+"\t}";
		String sourceClassName=sourceClass.getSimpleName();
		String descClassName=descClass.getSimpleName();
		String sCollectionClassName=sCollection.getSimpleName();
		String dCollectionClassName=dCollection.getSimpleName();
		String dCollectionFatherClassName=dCollectionClassName;
		String sCollectionFatherClassName=sCollectionClassName;
		{
			if(sCollectionClassName.endsWith("List")){
				sCollectionFatherClassName="List";
			}else if(sCollectionClassName.endsWith("Map")||sCollectionClassName.endsWith("Table")){
				sCollectionFatherClassName="Map";
			}else if(sCollectionClassName.endsWith("Set")){
				sCollectionFatherClassName="Set";
			}
			
			if(dCollectionClassName.endsWith("List")){
				dCollectionFatherClassName="List";
			}else if(dCollectionClassName.endsWith("Map")||dCollectionClassName.endsWith("Table")){
				dCollectionFatherClassName="Map";
			}else if(dCollectionClassName.endsWith("Set")){
				dCollectionFatherClassName="Set";
			}
		}
		String resultCollection=method.replace("{sourceClassName}",sourceClassName)
				.replace("{descClassName}",descClassName)
				.replace("{dCollectionFatherClassName}",dCollectionFatherClassName)
				.replace("{sCollectionFatherClassName}",sCollectionFatherClassName)
				.replace("{sCollectionClassName}",sCollectionClassName)
				.replace("{dCollectionClassName}",dCollectionClassName);
		
		
		System.out.println(resultCollection);
		
		
	}

	public static <T,U> void copy(Class<T> sourceClass, Class<U> descClass, Class<? extends Collection> sCollection) throws Exception {
		String method = "\tpublic static {collectionFatherClassName}<{descClassName}> copy({collectionFatherClassName}<{sourceClassName}> source){\n"
				+"\t\tif(source==null) return null;\n"
				+"\t\t{collectionFatherClassName}<{descClassName}> desc = new {collectionClassName}<>();\n"
				+"\t\tfor({sourceClassName} item : source ){\n"
				+"\t\t\t{descClassName} temp = copy(item);\n "
				+"\t\t\tdesc.add(temp);\n "
				+"\t\t}\n "
				+"\t\treturn desc; \n "
				+"\t}";
		String sourceClassName=sourceClass.getSimpleName();
		String descClassName=descClass.getSimpleName();
		String collectionClassName=sCollection.getSimpleName();
		String collectionFatherClassName=collectionClassName;
		if(collectionClassName.endsWith("List")){
			collectionFatherClassName="List";
		}else if(collectionClassName.endsWith("Map")||collectionClassName.endsWith("Table")){
			collectionFatherClassName="Map";
		}else if(collectionClassName.endsWith("Set")){
			collectionFatherClassName="Set";
		}
		String resultCollection=method.replace("{sourceClassName}",sourceClassName)
				.replace("{descClassName}",descClassName)
				.replace("{collectionClassName}",collectionClassName)
				.replace("{collectionFatherClassName}",collectionFatherClassName);
		System.out.println(resultCollection);
	}
	
	public static <T,U> String copy(Class<T> sourceClass,Class<U> descClass) throws Exception {
		String method ="\tpublic static {descClassName} copy({sourceClassName} source ) {\n"
				+"\t\tif(source==null) return null;\n"
				+"\t\t{descClassName} desc = new {descClassName}();\n"
				+"{methodItems} "
				+"\t\treturn desc; \n "
				+"\t}";
		String methodItem="\t\tdesc.{writeMethodName}(source.{getMethodName}());\n";
		
		String sourceClassName=sourceClass.getSimpleName();
		String descClassName=descClass.getSimpleName();
		StringBuilder methodItems=new StringBuilder("");
		
		PropertyDescriptor[] sourceProperties= Introspector.getBeanInfo(sourceClass).getPropertyDescriptors();
		PropertyDescriptor[] descProperties= Introspector.getBeanInfo(descClass).getPropertyDescriptors();
		Arrays.stream(sourceProperties).forEach(source-> {
			Arrays.stream(descProperties).forEach(desc->{
				if(source!=null&&desc!=null
						&&source.getName().equals(desc.getName())
						&&source.getPropertyType().isAssignableFrom(desc.getPropertyType())
						&&source.getReadMethod()!=null
						&&desc.getWriteMethod()!=null
						){//属性名一致
					methodItems.append(methodItem.replace("{writeMethodName}",desc.getWriteMethod().getName())
							.replace("{getMethodName}",source.getReadMethod().getName()));
				}
			});
		});
		String result=method.replace("{sourceClassName}",sourceClassName)
				.replace("{descClassName}",descClassName)
				.replace("{methodItems}",methodItems.toString());
		System.out.println(result);
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		{
//			Class<?> tClass= PlanVersionEntity.class;
//			Class<?> dClass=PlanVersionParam.class;
//			Class<ArrayList> cClass=ArrayList.class;
//			Class<HashSet> sClass=HashSet.class;
//			copy(tClass,dClass);
//			copy(tClass,dClass,cClass);
//		copy(tClass,dClass,cClass,sClass);
		}

		{
//			List<Map<String,Object>> list =new ArrayList<>();
//			for(int i=0;i<3;i++){
//				Map<String,Object> map=new HashMap<>();
//				map.put("id","10000");
//				map.put("name","名称");
//				map.put("linkName","");
//				map.put("wxLink","http://online.mc.com/mapp/market/index?id=1362");
//				map.put("appLink","http://online.mc.com/mapp/market/index?id=1362");
//				list.add(map);
//			}
//
//
//			Map<String,Object> contentJson=new HashMap<>();
//			contentJson.put("is_margin_top",true);
//			contentJson.put("is_center",false);
//			contentJson.put("content",list);
//
//			System.out.println(JsonUtil.toJson(contentJson));
		}
		{
//			String json="{\"appLink\":\"http://online.mc.com/mapp/market/index?id=1362\",\"wxLink\":\"http://online.mc.com/mapp/market/index?id=1362\",\"name\":\"名称\",\"id\":\"10000\",\"linkName\":\"\"}";
//			Map<String,Object>map=JsonUtil.parseJson(json,new TypeReference<Map<String,Object>>(){});
//			System.out.println(map);
			
		}
		
	}
}
