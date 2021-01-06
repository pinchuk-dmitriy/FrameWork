package service;

import model.User;

public class UserCreater {
    public static final String TESTDATA_USER_EMAIL = "test.data.user.email";
    public static final String TESTDATA_USER_PASSWORD = "test.data.user.password";
    public static final String TESTDATA_USER_BIRTH = "test.data.user.dateOfBirth";

    public static User withCredentialsFromProperty(){
        return new User(TestDataReader.getTestData(TESTDATA_USER_EMAIL), TestDataReader.getTestData(TESTDATA_USER_PASSWORD),
                TestDataReader.getTestData(TESTDATA_USER_BIRTH));
    }

}