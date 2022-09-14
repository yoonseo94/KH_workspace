--========================================
-- 실습문제 chun계정
--========================================
select * from tb_department; -- 학과
select * from tb_student; -- 학생 
--(tb_department.deprtment_no - tb_student.department_no)
--(tb_professor.professor_no - tb_student.coach_professor_no)
select * from tb_professor; -- 교수 
--(tb_department.department_no - tb_professor.department_no)
select * from tb_class; -- 수업
--(tb_department.department_no - tb_class.department_no)
select * from tb_class_professor; -- 수업 - 교수
--(tb_class.class_no - tb_class_professor.class_no)
--(tb_professor.professor_no - tb_class_professor.professor_no)
select * from tb_grade; -- 학점
--(tb_student.student_no -- tb_grade.student_no)
--(tb_class.class_no -- tb_grade.class_no)
