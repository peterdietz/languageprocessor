import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.client.utils.DateUtils;
import org.apache.logging.log4j.core.util.ArrayUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by peterdietz on 7/25/17.
 */
public class SearchSteps {
    List<Map<String,String>> books = new ArrayList<Map<String,String>>();

    @Given("^.+book with the title '(.+)', written by '(.+)', published in (.+)")
    public void addBook(String title, String author, String published) throws Throwable {
        Map<String, String> book = new HashMap<String, String>();
        book.put("title", title);
        book.put("author", author);
        book.put("date", published);
        books.add(book);
        System.out.println(books.size());
    }

    @When("^the customer searches for books published between (\\d+) and (\\d+)$")
    public void the_customer_searches_for_books_published_between_and(String from, String to) throws Throwable {

        for(Iterator<Map<String, String>> it = books.iterator(); it.hasNext();) {
            Map<String, String> entry = it.next();
            String published = entry.get("date");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fromDate = sdf.parse(from + "-01-01");
            Date toDate = sdf.parse(to + "-12-31");
            Date pubDate = DateUtils.parseDate(published, new String[]{"dd MMM yyyy"});

            if(fromDate.compareTo(pubDate) <= 0 && toDate.compareTo(pubDate) >= 0) {
                System.out.println("Accepted date: " + pubDate);

            } else {
                it.remove();
            }
        }

        System.out.println(books.size());
    }

    @Then("^(\\d+) books should have been found$")
    public void books_should_have_been_found(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertThat(books.size(), equalTo(arg1));
    }

    @Then("^Book (\\d+) should have the title '(.+)'$")
    public void book_should_have_the_title_Some_other_book(int pos, String title) throws Throwable {
        //reverse the order??
        Integer bookPosition = books.size() - pos;
        assert books.get(bookPosition).get("title").equalsIgnoreCase(title);
    }
}
