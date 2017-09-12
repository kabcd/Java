package eu.ase;

import java.lang.reflect.Method;

public class ProgMainAnnotation {
	public static void main(String[] args) {
		try{
			Class<Camera> obj=Camera.class;
			for(Method m:obj.getMethods())
			{
				System.out.println(m.getName());
				System.out.println(m.getDeclaringClass());
				for(Class i:m.getParameterTypes())
					System.out.println(i);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
			int passed = 0, failed = 0;
			Class<Camera> obj=Camera.class;
			for(Method m:obj.getMethods())
			{
				if(m.isAnnotationPresent(MyAnnotation.class))
				{
					try{
						m.invoke(null);
						passed++;
					}catch(Exception e){
						 System.out.printf("Test %s failed: %s %n", m, e.getCause());
			             failed++;
					}
				}
			}
		System.out.printf("Passed: %d, Failed %d%n", passed, failed);
	}
}
