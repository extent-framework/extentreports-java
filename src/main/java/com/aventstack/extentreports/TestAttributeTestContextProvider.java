package com.aventstack.extentreports;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.TestAttribute;
import com.aventstack.extentreports.model.TestAttributeTestContext;

/**
 * Uses an attribute context for {@link TestAttribute} (Category, Device, Author etc.) 
 * and tracks the collection of tests segregated by the type {@link TestAttribute}
 *
 * @param <T> A {@link TestAttribute} type
 */
public class TestAttributeTestContextProvider<T extends TestAttribute> {
    
    private List<TestAttributeTestContext<T>> testAttrCollection;
    
    public TestAttributeTestContextProvider() { 
        testAttrCollection = new ArrayList<>();
    }
    
    public void setAttributeContext(T attr, Test test) {
        Optional<TestAttributeTestContext<T>> testOptionalTestContext = testAttrCollection
                .stream()
                .filter(x -> x.getName().equals(attr.getName()))
                .findFirst();
        
        if (testOptionalTestContext.isPresent()) {
            List<Test> testList = testOptionalTestContext.get().getTestList();
            
            boolean b = testList
                    .stream()
                    .anyMatch(t -> t.getID() == test.getID());
            
            if (!b) {
                testOptionalTestContext.get().setTest(test);
            }
            testOptionalTestContext.get().refreshTestStatusCounts();
        }
        else {
            TestAttributeTestContext<T> testAttrContext = new TestAttributeTestContext<>(attr);
            testAttrContext.setTest(test);
            testAttrCollection.add(testAttrContext);
        }
    }
    
    public void removeTest(Test test) {
		Iterator<TestAttributeTestContext<T>> iter = testAttrCollection.iterator();
		while (iter.hasNext()) {
			TestAttributeTestContext<T> context = iter.next();
			TestRemover.remove(context.getTestList(), test);
			if (context.isEmpty()) {
				iter.remove();
			}
    	}
    }
    
    public List<TestAttributeTestContext<T>> getTestAttributeTestContextList() {
        return testAttrCollection;
    }
    
}
