package cn.jorian.jorianframework.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author jorian
 */
@Configuration
public class CustomerMvcConfigurerAdapter implements WebMvcConfigurer {

	/**
	 * 上传文件根路径
	 */
	@Value("${files.path}")
	private String filesPath;

	/**
	 * 添加静态资源文件，外部可以直接访问地址
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//其他静态资源，与本文关系不大
		registry.addResourceHandler("/upload/**").addResourceLocations(ResourceUtils.FILE_URL_PREFIX + filesPath + File.separator);

		//需要告知系统，这是要被当成静态文件的！
		//第一个方法设置访问路径前缀，第二个方法设置资源路径
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}


}
