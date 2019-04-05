package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import runners.RunnerCT001Test;
import runners.RunnerCT002Test;
import runners.RunnerCT003Test;
import runners.RunnerCT004Test;
import runners.RunnerCT005Test;
import runners.RunnerCT006Test;
import runners.RunnerCT007Test;
import runners.RunnerCT008Test;
import runners.RunnerCT009Test;


@RunWith(Suite.class)
@SuiteClasses({	
	RunnerCT001Test.class,
	RunnerCT002Test.class,
	RunnerCT003Test.class,
	RunnerCT004Test.class,
	RunnerCT005Test.class,
	RunnerCT006Test.class,
	RunnerCT007Test.class,
	RunnerCT008Test.class,
	RunnerCT009Test.class	
})
public class SuiteTestes {

}
