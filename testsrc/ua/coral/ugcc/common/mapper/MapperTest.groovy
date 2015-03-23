package ua.coral.ugcc.common.mapper

import com.google.appengine.api.datastore.Entity
import com.google.appengine.api.datastore.ShortBlob
import com.google.appengine.api.datastore.Text
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig
import com.google.appengine.tools.development.testing.LocalServiceTestHelper
import spock.lang.Specification
import ua.coral.ugcc.common.dto.impl.News

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
        entity.getProperty("title") == new ShortBlob(news.getTitle().getBytes("UTF-8"))
        entity.getProperty("content") == new Text(news.getContent())

        helper.tearDown()
    }

    def 'should get News from Entity'() {
        setup:
        Entity entity = new Entity("News", 123l)
        entity.setProperty("id", 123l)
        entity.setProperty("title", new ShortBlob("testTitle".getBytes("UTF-8")))
        entity.setProperty("content", new Text("testContent"))

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
