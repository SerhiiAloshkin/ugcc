package ua.coral.ugcc.common.mapper

import spock.lang.Specification
import ua.coral.ugcc.common.dto.impl.News

import com.google.appengine.api.datastore.Entity
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig
import com.google.appengine.tools.development.testing.LocalServiceTestHelper

class MapperTest extends Specification {

    LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig())

    def setup() {
        helper.setUp()
    }

    def 'should get Entity from News'() {
        setup:
        News news = new News(id: 123l, title: "testTitle", content: "testContent")

        when:
        Entity entity = Mapper.getEntity(news)

        then:
        entity != null
        entity.getProperty("id") == news.getId()
        entity.getProperty("title") == news.getTitle()
        entity.getProperty("content") == news.getContent()

        helper.tearDown()
    }

    def 'should get News from Entity'() {
        setup:
        Entity entity = new Entity("News", 123l)
        entity.setProperty("id", 123l)
        entity.setProperty("title", "testTitle")
        entity.setProperty("content", "testContent")

        when:
        News news = Mapper.getObject(News.class, entity)

        then:
        news != null
        with(news) {
            id == 123l
            title == "testTitle"
            content == "testContent"
        }

        helper.tearDown()
    }
}
