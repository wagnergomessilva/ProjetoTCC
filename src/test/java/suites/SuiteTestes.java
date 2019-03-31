package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import runners.RunnerCT001Test;
import runners.RunnerCT002Test;
import runners.RunnerCT003Test;
import runners.RunnerCT004Test;
import runners.RunnerCT005Test;


@RunWith(Suite.class)
@SuiteClasses({	
	//RunnerCT001Test.class,
	//RunnerCT002Test.class,
	//RunnerCT003Test.class,
	//RunnerCT004Test.class,
	RunnerCT005Test.class
})
public class SuiteTestes {

}
