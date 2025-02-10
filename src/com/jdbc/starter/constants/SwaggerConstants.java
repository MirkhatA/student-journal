package com.jdbc.starter.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SwaggerConstants {

    public static final String STUDENTS_TAG = "Students";
    public static final String STUDENTS_DESCRIPTION = "API for managing students";

    public static final String GROUPS_TAG = "Groups";
    public static final String GROUPS_DESCRIPTION = "API for managing student groups";

    public static final String GRADES_TAG = "Grades";
    public static final String GRADES_DESCRIPTION = "API for managing student grades";

    public static final String GRADE_ID_DESCRIPTION = "Grade ID";
    public static final String GROUP_ID_DESCRIPTION = "Group ID";
    public static final String STUDENT_ID_DESCRIPTION = "Student ID";
    public static final String NAME_TO_SEARCH_DESCRIPTION = "Name to search for";

    public static final String GET_ALL_STUDENTS_SUMMARY = "Get all students";
    public static final String GET_ALL_STUDENTS_DESC = "Returns a list of all registered students.";

    public static final String GET_STUDENT_BY_ID_SUMMARY = "Get student by ID";
    public static final String GET_STUDENT_BY_ID_DESC = "Returns details of a student by their ID.";

    public static final String GET_STUDENTS_BY_GROUP_SUMMARY = "Get students by group";
    public static final String GET_STUDENTS_BY_GROUP_DESC = "Returns a list of students belonging to a specific group.";

    public static final String FIND_STUDENTS_BY_NAME_SUMMARY = "Find students by name";
    public static final String FIND_STUDENTS_BY_NAME_DESC = "Searches for students by first or last name.";

    public static final String CREATE_STUDENT_SUMMARY = "Create a new student";
    public static final String CREATE_STUDENT_DESC = "Adds a new student to the database.";

    public static final String UPDATE_STUDENT_SUMMARY = "Update student details";
    public static final String UPDATE_STUDENT_DESC = "Updates the details of an existing student by ID.";

    public static final String DELETE_STUDENT_SUMMARY = "Delete student";
    public static final String DELETE_STUDENT_DESC = "Removes a student from the database by ID.";

    public static final String GET_ALL_GROUPS_SUMMARY = "Get all groups";
    public static final String GET_ALL_GROUPS_DESC = "Returns a list of all student groups.";

    public static final String GET_GROUP_BY_ID_SUMMARY = "Get group by ID";
    public static final String GET_GROUP_BY_ID_DESC = "Returns details of a specific group by ID.";

    public static final String CREATE_GROUP_SUMMARY = "Create a new group";
    public static final String CREATE_GROUP_DESC = "Adds a new group to the database.";

    public static final String UPDATE_GROUP_SUMMARY = "Update group details";
    public static final String UPDATE_GROUP_DESC = "Updates the details of an existing group by ID.";

    public static final String DELETE_GROUP_SUMMARY = "Delete group";
    public static final String DELETE_GROUP_DESC = "Removes a group from the database by ID.";

    public static final String GET_ALL_GRADES_SUMMARY = "Get all grades";
    public static final String GET_ALL_GRADES_DESC = "Returns a list of all student grades.";

    public static final String GET_GRADE_BY_ID_SUMMARY = "Get grade by ID";
    public static final String GET_GRADE_BY_ID_DESC = "Returns details of a specific grade by ID.";

    public static final String CREATE_GRADE_SUMMARY = "Create a new grade";
    public static final String CREATE_GRADE_DESC = "Adds a new grade for a student.";

    public static final String UPDATE_GRADE_SUMMARY = "Update grade details";
    public static final String UPDATE_GRADE_DESC = "Updates the details of an existing grade by ID.";

    public static final String DELETE_GRADE_SUMMARY = "Delete grade";
    public static final String DELETE_GRADE_DESC = "Removes a grade from the database by ID.";
}
