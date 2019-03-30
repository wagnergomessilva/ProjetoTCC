package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import runners.RunnerCT001Test;
import runners.RunnerCT002Test;
import runners.RunnerCT003Test;


@RunWith(Suite.class)
@SuiteClasses({	
	//RunnerCT001Test.class,
	//RunnerCT002Test.class,
	RunnerCT003Test.class
})
public class SuiteTestes {

}
