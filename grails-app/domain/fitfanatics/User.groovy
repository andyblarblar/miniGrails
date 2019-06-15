package fitfanatics

class User {
    String name
    Integer age
    String gender
    Double height
    Integer weight
    Integer goalWeight;
    Integer goalDays;
    String workoutType;

    //static belongsTo = [dbauth:DbAuth] removed to allow for updates
    static constraints = {
    }

}


