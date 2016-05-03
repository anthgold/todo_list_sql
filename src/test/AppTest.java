import java.util.ArrayList;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Before
  public void setUp() {
    DB.sql2o = new sql2o("jdbc:postgresql://localhost:5432/to_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.spq2o.open()) {
      String deleteTasksQuery = "DELETE FROM tasks *;";
      String deleteCategoriesQuery = "DELETE FROM categories *;";
      con.createQuery(deleteTasksQuery).executeUpdate();
      con.createQuery(deleteCategoriesQuery).executeUpdate();
    }
  }

 //  @Test
 // public void rootTest() {
 //   goTo("http://localhost:4567/");
 //   assertThat(pageSource()).contains("Todo list!");
 //   assertThat(pageSource()).contains("View Category List");
 //   assertThat(pageSource()).contains("Add a New Category");
 // }
}
