package com.aventstack.extentreports;

import java.util.List;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.Test;

/**
 * <p>
 * This class maintains the total count of tests and its nodes along with their
 * statuses for a given run session
 */
public class ReportStatusStats {

	private List<Test> testList;
	private AnalysisStrategy strategy = AnalysisStrategy.TEST;

	private int parentPass = 0;
	private int parentFail = 0;
	private int parentFatal = 0;
	private int parentError = 0;
	private int parentWarning = 0;
	private int parentSkip = 0;
	private int parentExceptions = 0;

	private int childPass = 0;
	private int childFail = 0;
	private int childFatal = 0;
	private int childError = 0;
	private int childWarning = 0;
	private int childSkip = 0;
	private int childInfo = 0;
	private int childDebug = 0;
	private int childExceptions = 0;

	private int grandChildPass = 0;
	private int grandChildFail = 0;
	private int grandChildFatal = 0;
	private int grandChildError = 0;
	private int grandChildWarning = 0;
	private int grandChildSkip = 0;
	private int grandChildInfo = 0;
	private int grandChildDebug = 0;
	private int grandChildExceptions = 0;

	private int eventsPass = 0;
	private int eventsFail = 0;
	private int eventsFatal = 0;
	private int eventsError = 0;
	private int eventsWarning = 0;
	private int eventsSkip = 0;
	private int eventsInfo = 0;
	private int eventsDebug = 0;
	private int eventsExceptions = 0;

	public ReportStatusStats(AnalysisStrategy strategy) {
		this.strategy = strategy;
	}

	public void refresh(List<Test> testList) {
		reset();
		this.testList = testList;
		refreshStats();
	}

	private void reset() {
		parentPass = 0;
		parentFail = 0;
		parentFatal = 0;
		parentError = 0;
		parentWarning = 0;
		parentSkip = 0;
		parentExceptions = 0;

		childPass = 0;
		childFail = 0;
		childFatal = 0;
		childError = 0;
		childWarning = 0;
		childSkip = 0;
		childInfo = 0;
		childExceptions = 0;

		grandChildPass = 0;
		grandChildFail = 0;
		grandChildFatal = 0;
		grandChildError = 0;
		grandChildWarning = 0;
		grandChildSkip = 0;
		grandChildInfo = 0;
		grandChildExceptions = 0;

		eventsPass = 0;
		eventsFail = 0;
		eventsFatal = 0;
		eventsError = 0;
		eventsWarning = 0;
		eventsSkip = 0;
		eventsInfo = 0;
		eventsDebug = 0;
		eventsExceptions = 0;
	}

	public int getParentCount() {
		return getParentCountPass() + getParentCountFail() + getParentCountFatal() + getParentCountError()
				+ getParentCountWarning() + getParentCountSkip();
	}

	public int getParentCountPass() {
		return parentPass;
	}

	public int getParentCountFail() {
		return parentFail;
	}

	public int getParentCountFatal() {
		return parentFatal;
	}

	public int getParentCountError() {
		return parentError;
	}

	public int getParentCountWarning() {
		return parentWarning;
	}

	public int getParentCountSkip() {
		return parentSkip;
	}

	public int getParentCountExceptions() {
		return parentExceptions;
	}

	public float getParentPercentagePass() {
		float p = getParentCount() > 0 ? (float) getParentCountPass() / (float) getParentCount() : 0;
		return p * 100;
	}

	public float getParentPercentageFail() {
		float p = getParentCount() > 0
				? ((float) getParentCountFail() + (float) getParentCountFatal()) / (float) getParentCount()
				: 0;
		return p * 100;
	}

	public float getParentPercentageOthers() {
		float p = getParentCount() > 0
				? ((float) getParentCountWarning() + (float) getParentCountError()) / (float) getParentCount()
				: 0;
		return p * 100;
	}

	public float getParentPercentageSkip() {
		float p = getParentCount() > 0 ? (float) getParentCountSkip() / (float) getParentCount() : 0;
		return p * 100;
	}

	public int getChildCount() {
		return getChildCountPass() + getChildCountFail() + getChildCountFatal() + getChildCountError()
				+ getChildCountWarning() + getChildCountSkip() + getChildCountInfo();
	}

	public int getChildCountPass() {
		return childPass;
	}

	public int getChildCountFail() {
		return childFail;
	}

	public int getChildCountFatal() {
		return childFatal;
	}

	public int getChildCountError() {
		return childError;
	}

	public int getChildCountWarning() {
		return childWarning;
	}

	public int getChildCountSkip() {
		return childSkip;
	}

	public int getChildCountInfo() {
		return childInfo;
	}

	public int getChildCountDebug() {
		return childDebug;
	}

	public int getChildCountExceptions() {
		return childExceptions;
	}

	public float getChildPercentagePass() {
		float p = getChildCount() > 0 ? (float) getChildCountPass() / (float) getChildCount() : 0;
		return p * 100;
	}

	public float getChildPercentageFail() {
		float p = getChildCount() > 0
				? ((float) getChildCountFail() + (float) getChildCountFatal()) / (float) getChildCount()
				: 0;
		return p * 100;
	}

	public float getChildPercentageOthers() {
		float p = getChildCount() > 0
				? (((float) getChildCountWarning() + (float) getChildCountError() + (float) getChildCountSkip()
						+ (float) getChildCountInfo()) / (float) getChildCount())
				: 0;
		return p * 100;
	}

	public float getChildPercentageSkip() {
		float p = getChildCount() > 0 ? (float) getChildCountSkip() / (float) getChildCount() : 0;
		return p * 100;
	}

	public int getGrandChildCount() {
		return getGrandChildCountPass() + getGrandChildCountFail() + getGrandChildCountFatal()
				+ getGrandChildCountError() + getGrandChildCountWarning() + getGrandChildCountSkip()
				+ getGrandChildCountInfo();
	}

	public int getGrandChildCountPass() {
		return grandChildPass;
	}

	public int getGrandChildCountFail() {
		return grandChildFail;
	}

	public int getGrandChildCountFatal() {
		return grandChildFatal;
	}

	public int getGrandChildCountError() {
		return grandChildError;
	}

	public int getGrandChildCountWarning() {
		return grandChildWarning;
	}

	public int getGrandChildCountSkip() {
		return grandChildSkip;
	}

	public int getGrandChildCountInfo() {
		return grandChildInfo;
	}

	public int getGrandChildCountDebug() {
		return grandChildDebug;
	}

	public int getGrandChildCountExceptions() {
		return grandChildExceptions;
	}

	public float getGrandChildPercentagePass() {
		float p = getGrandChildCount() > 0 ? (float) getGrandChildCountPass() / (float) getGrandChildCount() : 0;
		return p * 100;
	}

	public float getGrandChildPercentageFail() {
		float p = getGrandChildCount() > 0
				? ((float) getGrandChildCountFail() + (float) getGrandChildCountFatal()) / (float) getGrandChildCount()
				: 0;
		return p * 100;
	}

	public float getGrandChildPercentageOthers() {
		float p = getGrandChildCount() > 0
				? (((float) getGrandChildCountWarning() + (float) getGrandChildCountError()
						+ (float) getGrandChildCountSkip() + (float) getGrandChildCountInfo())
						/ (float) getGrandChildCount())
				: 0;
		return p * 100;
	}

	public float getGrandChildPercentageSkip() {
		float p = getGrandChildCount() > 0 ? (float) getGrandChildCountSkip() / (float) getGrandChildCount() : 0;
		return p * 100;
	}
	
	public int getEventsCount() {
		return getEventsCountPass() + getEventsCountFail() + getEventsCountFatal()
				+ getEventsCountError() + getEventsCountWarning() + getEventsCountSkip()
				+ getEventsCountInfo();
	}

	public int getEventsCountPass() {
		return eventsPass;
	}

	public int getEventsCountFail() {
		return eventsFail;
	}

	public int getEventsCountFatal() {
		return eventsFatal;
	}

	public int getEventsCountError() {
		return eventsError;
	}

	public int getEventsCountWarning() {
		return eventsWarning;
	}

	public int getEventsCountSkip() {
		return eventsSkip;
	}

	public int getEventsCountInfo() {
		return eventsInfo;
	}

	public int getEventsCountDebug() {
		return eventsDebug;
	}

	public int getEventsCountExceptions() {
		return eventsExceptions;
	}

	public float getEventsPercentagePass() {
		float p = getEventsCount() > 0 ? (float) getEventsCountPass() / (float) getEventsCount() : 0;
		return p * 100;
	}

	public float getEventsPercentageFail() {
		float p = getEventsCount() > 0
				? ((float) getEventsCountFail() + (float) getEventsCountFatal()) / (float) getEventsCount()
				: 0;
		return p * 100;
	}

	public float getEventsPercentageOthers() {
		float p = getEventsCount() > 0
				? (((float) getEventsCountWarning() + (float) getEventsCountError()
						+ (float) getEventsCountSkip() + (float) getEventsCountInfo())
						/ (float) getEventsCount())
				: 0;
		return p * 100;
	}

	public float getEventsPercentageSkip() {
		float p = getEventsCount() > 0 ? (float) getEventsCountSkip() / (float) getEventsCount() : 0;
		return p * 100;
	}

	private void refreshStats() {
		testList.forEach(this::addTestForStatusStatsUpdate);
	}

	private void addTestForStatusStatsUpdate(Test test) {
		updateEventsCount(test);

		if (test.getBddType() != null
				|| (!test.getNodeContext().isEmpty() && test.getNodeContext().get(0).getBddType() != null)) {
			updateGroupCountsBDD(test);
			return;
		}

		if (strategy == AnalysisStrategy.TEST || strategy == AnalysisStrategy.CLASS) {
			updateGroupCountsTestStrategy(test);
			return;
		}

		if (strategy == AnalysisStrategy.SUITE) {
			updateGroupCountsSuiteStrategy(test);
			return;
		}

		throw new InvalidAnalysisStrategyException("No such strategy found: " + strategy);
	}

	private synchronized void updateEventsCount(Test test) {
		test.getLogContext().getAll().stream()
			.map(Log::getStatus)
			.forEach(this::incrementEvent);
		for (Test node : test.getNodeContext().getAll()) {
			updateEventsCount(node);
		}
	}

	private void updateGroupCountsSuiteStrategy(Test test) {
		incrementItemCountByStatus(ItemLevel.PARENT, test.getStatus());

		if (!test.getNodeContext().isEmpty()) {
			for (Test x : test.getNodeContext().getAll()) {
				incrementItemCountByStatus(ItemLevel.CHILD, x.getStatus());
				if (!x.getNodeContext().isEmpty()) {
					x.getNodeContext().getAll()
							.forEach(n -> incrementItemCountByStatus(ItemLevel.GRANDCHILD, n.getStatus()));
				}
			}
		}
	}

	private void updateGroupCountsBDD(Test test) {
		incrementItemCountByStatus(ItemLevel.PARENT, test.getStatus());

		if (!test.getNodeContext().isEmpty()) {
			for (Test x : test.getNodeContext().getAll()) {
				if (x.getBddType() == Scenario.class) {
					incrementItemCountByStatus(ItemLevel.CHILD, x.getStatus());
				}
				if (!x.getNodeContext().isEmpty()) {
					for (Test n : x.getNodeContext().getAll()) {
						if (n.getBddType() == Scenario.class) {
							incrementItemCountByStatus(ItemLevel.CHILD, n.getStatus());
							n.getNodeContext().getAll()
									.forEach(z -> incrementItemCountByStatus(ItemLevel.GRANDCHILD, z.getStatus()));
						} else {
							incrementItemCountByStatus(ItemLevel.GRANDCHILD, n.getStatus());
						}
					}
				}
			}
		}
	}

	private void updateGroupCountsTestStrategy(Test test) {
		incrementItemCountByStatus(ItemLevel.PARENT, test.getStatus());

		if (!test.getNodeContext().isEmpty()) {
			updateGroupCountsTestStrategyChildren(test);
		}
	}

	private void updateGroupCountsTestStrategyChildren(Test test) {
		test.getNodeContext().getAll().forEach(x -> {
			if (x.getNodeContext().isEmpty()) {
				incrementItemCountByStatus(ItemLevel.CHILD, x.getStatus());
			} else {
				updateGroupCountsTestStrategyChildren(x);
			}
		});
	}

	enum ItemLevel {
		PARENT, CHILD, GRANDCHILD
	}

	private void incrementItemCountByStatus(ItemLevel item, Status status) {
		switch (item) {
		case PARENT:
			incrementParent(status);
			break;
		case CHILD:
			incrementChild(status);
			break;
		case GRANDCHILD:
			incrementGrandChild(status);
			break;
		default:
			break;
		}
	}

	private void incrementParent(Status status) {
		switch (status) {
		case PASS:
			parentPass++;
			return;
		case FAIL:
			parentFail++;
			break;
		case FATAL:
			parentFatal++;
			break;
		case ERROR:
			parentError++;
			break;
		case WARNING:
			parentWarning++;
			break;
		case SKIP:
			parentSkip++;
			break;
		default:
			break;
		}

		parentExceptions++;
	}

	private void incrementChild(Status status) {
		switch (status) {
		case PASS:
			childPass++;
			break;
		case FAIL:
			childFail++;
			break;
		case FATAL:
			childFatal++;
			break;
		case ERROR:
			childError++;
			break;
		case WARNING:
			childWarning++;
			break;
		case SKIP:
			childSkip++;
			break;
		case INFO:
			childInfo++;
			break;
		case DEBUG:
			childDebug++;
			break;
		default:
			break;
		}

		if (status != Status.PASS && status != Status.INFO)
			childExceptions++;
	}

	private void incrementGrandChild(Status status) {
		switch (status) {
		case PASS:
			grandChildPass++;
			break;
		case FAIL:
			grandChildFail++;
			break;
		case FATAL:
			grandChildFatal++;
			break;
		case ERROR:
			grandChildError++;
			break;
		case WARNING:
			grandChildWarning++;
			break;
		case SKIP:
			grandChildSkip++;
			break;
		case INFO:
			grandChildInfo++;
			break;
		case DEBUG:
			grandChildDebug++;
			break;
		default:
			break;
		}

		if (status != Status.PASS && status != Status.INFO)
			grandChildExceptions++;
	}

	private void incrementEvent(Status status) {
		switch (status) {
		case PASS:
			eventsPass++;
			break;
		case FAIL:
			eventsFail++;
			break;
		case FATAL:
			eventsFatal++;
			break;
		case ERROR:
			eventsError++;
			break;
		case WARNING:
			eventsWarning++;
			break;
		case SKIP:
			eventsSkip++;
			break;
		case INFO:
			eventsInfo++;
			break;
		case DEBUG:
			eventsDebug++;
			break;
		default:
			break;
		}
	}
}