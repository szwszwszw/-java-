/**
 * bean和map之间互相转换
 * @author szw
 */
public class SwitchBeanAndMapUtils {
	/**
	 * 把Javabean对象转换成Map对象
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> bean2map(Object bean) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		BeanInfo beanInfo=Introspector.getBeanInfo(bean.getClass(),Object.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor pd:pds){
			String propertyName=pd.getName();//属性名称
			Object propertyValue=pd.getReadMethod().invoke(bean);//调用getter方法，获取属性值
			map.put(propertyName, propertyValue);
		}		
		return map;
	}
	/**
	 * 将Map对象转换成Javabean对象
	 * @param map
	 * @param beanType
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T map2bean(Map<String, Object> map,Class<T> beanType) throws Exception{
		Object obj=beanType.newInstance();//创建Javabean对象
		BeanInfo beanInfo=Introspector.getBeanInfo(beanType,Object.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();	
		for(PropertyDescriptor pd:pds){
			Object value=map.get(pd.getName());//从map中获取和属性同名的key值
			pd.getWriteMethod().invoke(obj, value);//调用setter方法，设置属性值
		}	
		return (T)obj;
	}
