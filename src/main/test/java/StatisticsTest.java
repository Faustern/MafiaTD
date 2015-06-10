import com.tyhyidon.faust.game.ApplicationWeb;
import com.tyhyidon.faust.game.entity.enums.Season;
import com.tyhyidon.faust.game.model.statistics.MemberStatistics;
import com.tyhyidon.faust.game.services.StatisticsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by vasylsavytskyi on 07.06.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationWeb.class)
public class StatisticsTest {

    @Autowired
    StatisticsServiceImpl statisticsService;

    @Test
    public void stubTest() throws IllegalAccessException, InstantiationException {
        MemberStatistics memberStatistics = statisticsService.getMemberStatistics("Амахонда", Season.WINTER_13_14);
        memberStatistics.getBestVoices();
    }
}
