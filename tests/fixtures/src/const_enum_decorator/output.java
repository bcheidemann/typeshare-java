package com.typeshare.java;

public class Namespace {

	public enum BestHockeyTeams {
		PittsburghPenguins
	}

	@Getter
	public enum BestHockeyTeams1 {
		PittsburghPenguins
	}

	@JsonAdapter(MyCustomAdapter.class)
	public enum BestHockeyTeams2 {
		PittsburghPenguins
	}

	@Getter
	@JsonAdapter(MyCustomAdapter.class)
	public enum BestHockeyTeams3 {
		PittsburghPenguins
	}

	@DecoratorWithStringArgument("test")
	public enum BestHockeyTeams4 {
		PittsburghPenguins
	}

}
