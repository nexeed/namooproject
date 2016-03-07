/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:hkkang@nextree.co.kr">Kang Hyoungkoo</a>
 * @since 2015. 01. 16.
 */

package namoo.board.dom1.comp.lifecycle;

import namoo.board.dom1.lifecycle.BoardServiceLifecycler;
import namoo.board.dom1.lifecycle.BoardStoreLifecycler;
import namoo.board.dom1.service.PostingService;
import namoo.board.dom1.service.SocialBoardService;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

public class BoardSpringServiceLifecycler implements BoardServiceLifecycler {

	private static BoardSpringServiceLifecycler serviceLifecycler;
	private GenericApplicationContext context;

	// --------------------------------------------------------------------------

	private BoardSpringServiceLifecycler(BoardStoreLifecycler storeLifecycler) {
		//
		context = new GenericApplicationContext();
		context.getBeanFactory().registerSingleton("storeLifecycler",
				storeLifecycler);

		BeanDefinitionReader beanDefReader = new XmlBeanDefinitionReader(
				context);
		beanDefReader.loadBeanDefinitions("/applicationContext.xml");
		context.refresh();
	}

	public static BoardServiceLifecycler getInstance(
			BoardStoreLifecycler storeLifecycler) {
		//
		if (serviceLifecycler == null) {
			serviceLifecycler = new BoardSpringServiceLifecycler(
					storeLifecycler);
		}

		return serviceLifecycler;
	}

	@Override
	public SocialBoardService createSocialBoardService() {
		//
		return context.getBean(SocialBoardService.class);
	}

	@Override
	public PostingService createPostingService() {
		//
		return context.getBean(PostingService.class);
	}

}
