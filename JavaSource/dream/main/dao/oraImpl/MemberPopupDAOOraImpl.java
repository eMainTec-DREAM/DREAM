package dream.main.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.CommonUtil;
import common.util.QueryBuffer;
import dream.main.dao.MemberPopupDAO;
import dream.main.dto.MemberPopupDTO;

/**
 * main화면의 member button의 user detail 화면
 * @author  freeze
 * @version $Id: MemberPopupDAO.java,v 1.1 2013/08/30 09:13:03 javaworker Exp $
 * @since   1.0
 *
 * @spring.bean id="memberPopupDAOTarget"
 * @spring.txbn id="memberPopupDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MemberPopupDAOOraImpl extends BaseJdbcDaoSupportOra implements MemberPopupDAO
{
    /**
     * 유저 상세 조회
     * @author  freeze
     * @version $Id: MemberPopupDAO.java,v 1.1 2013/08/30 09:13:03 javaworker Exp $
     * @since   1.0
     * 
     * @param userID
     * @return
     */
	public MemberPopupDTO findDetail(String userID) 
	{
		QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT                                    							");
	    query.append("       a.user_id userID,    											");
	    query.append("       a.user_name userName,    										");
	    query.append("       a.user_group userGroup,    									");
		query.append("       a.dept_no deptNo,    											");
	    query.append("      (SELECT description     										");
	    query.append("       FROM   T4DEPT     										        ");
	    query.append("       WHERE  dept_no = a.dept_no) deptName,    			            ");
	    query.append("       a.grade grade,    												");
	    query.append("       a.craft craft,    												");
	    query.append("       a.email email,    											    ");
	    query.append("       a.address address,    											");
	    query.append("       a.join_date joinDate,    										");
	    query.append("       a.retire_date retireDate,    									");
	    query.append("       a.user_password password,    									");
	    query.append("       a.birthday birthDay    										");
	    query.append("FROM   T4USERS a    													");
	    query.append("WHERE  a.user_id = '" + userID + "'    								");
        
        return (MemberPopupDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MemberPopupDTO()));
	}
	
    /**
     * 유저정보 상세 수정
     * @author  freeze
     * @version $Id: MemberPopupDAO.java,v 1.1 2013/08/30 09:13:03 javaworker Exp $
     * @since   1.0
     * 
     * @param mgUserDetailDTO
     * @return
     */
	public int updateDetail(MemberPopupDTO memberPopupDTO) 
	{
		QueryBuffer query = new QueryBuffer();
        query.append("UPDATE T4USERS            		");
        query.append("SET    dept_no      	= ?,        ");
        query.append("       grade      	= ?,      	");
        query.append("       craft      	= ?,      	");
        query.append("       email      	= ?,      	");
        query.append("       address      	= ?,      	");
        query.append("       join_date      = ?,        ");
        query.append("       retire_date    = ?,        ");
        query.append("       user_password  = ?,        ");
        query.append("       user_name      = ?,        ");
        query.append("       birthday     	= ?        	");
        query.append("WHERE  user_id = ?          		");

        Object[] objects = new Object[] {
                memberPopupDTO.getDeptNo(),
                memberPopupDTO.getGrade(),  			
                memberPopupDTO.getCraft(),
                memberPopupDTO.getEmail(),
                memberPopupDTO.getAddress(),
                memberPopupDTO.getJoinDate(),
                memberPopupDTO.getRetireDate(),  			
                CommonUtil.aesEncodeString(memberPopupDTO.getPassword()),
                memberPopupDTO.getUserName(),  			
                memberPopupDTO.getBirthDay(),  			
                memberPopupDTO.getUserID()
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
	}
}