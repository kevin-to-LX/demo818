package com.hik.demo.dao.pojo;

public class UserLogin {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_login.id
     *
     * @mbggenerated Fri Aug 10 00:58:49 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_login.user_name
     *
     * @mbggenerated Fri Aug 10 00:58:49 CST 2018
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_login.user_psw
     *
     * @mbggenerated Fri Aug 10 00:58:49 CST 2018
     */
    private String userPsw;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_login.user_level
     *
     * @mbggenerated Fri Aug 10 00:58:49 CST 2018
     */
    private Integer userLevel;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_login.id
     *
     * @return the value of user_login.id
     *
     * @mbggenerated Fri Aug 10 00:58:49 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_login.id
     *
     * @param id the value for user_login.id
     *
     * @mbggenerated Fri Aug 10 00:58:49 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_login.user_name
     *
     * @return the value of user_login.user_name
     *
     * @mbggenerated Fri Aug 10 00:58:49 CST 2018
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_login.user_name
     *
     * @param userName the value for user_login.user_name
     *
     * @mbggenerated Fri Aug 10 00:58:49 CST 2018
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_login.user_psw
     *
     * @return the value of user_login.user_psw
     *
     * @mbggenerated Fri Aug 10 00:58:49 CST 2018
     */
    public String getUserPsw() {
        return userPsw;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_login.user_psw
     *
     * @param userPsw the value for user_login.user_psw
     *
     * @mbggenerated Fri Aug 10 00:58:49 CST 2018
     */
    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_login.user_level
     *
     * @return the value of user_login.user_level
     *
     * @mbggenerated Fri Aug 10 00:58:49 CST 2018
     */
    public Integer getUserLevel() {
        return userLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_login.user_level
     *
     * @param userLevel the value for user_login.user_level
     *
     * @mbggenerated Fri Aug 10 00:58:49 CST 2018
     */
    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }
}