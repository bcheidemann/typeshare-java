#[typeshare]
pub enum BestHockeyTeams {
    PittsburghPenguins,
}

#[typeshare(java(annotations = "@Getter"))]
pub enum BestHockeyTeams1 {
    PittsburghPenguins,
}

#[typeshare(java(annotations = "@JsonAdapter(MyCustomAdapter.class)"))]
pub enum BestHockeyTeams2 {
    PittsburghPenguins,
}

#[typeshare(java(annotations = "
    @Getter
    @JsonAdapter(MyCustomAdapter.class)
"))]
pub enum BestHockeyTeams3 {
    PittsburghPenguins,
}

#[typeshare(java(annotations = "@DecoratorWithStringArgument(\"test\")"))]
pub enum BestHockeyTeams4 {
    PittsburghPenguins,
}
