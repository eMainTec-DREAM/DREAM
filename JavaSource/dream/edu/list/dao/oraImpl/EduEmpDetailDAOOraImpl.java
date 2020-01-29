package dream.edu.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;

import dream.edu.list.dao.EduEmpDetailDAO;
import dream.edu.list.dto.EduCommonDTO;
import dream.edu.list.dto.EduEmpDetailDTO;

/**
 * 작업결과-작업자 상세 dao
 * @author  kim21017
 * @version $Id: EduEmpDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="eduEmpDetailDAOTarget"
 * @spring.txbn id="eduEmpDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class EduEmpDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements EduEmpDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: EduEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param wkOrId
     * @param eduEmpId
     * @param compNo
     * @return
     */
    public EduEmpDetailDTO findDetail(String empTrainListId, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("SELECT                                                       ");
        query.append("     '' seqNo                                                ");
        query.append("    ,'' isDelCheck                                           ");
        query.append("    ,a.emptrainlist_id               as empTrainListId       ");
        query.append("    ,a.emp_id                        as empId                 ");
        query.append("    ,b.emp_no                        as empNo                 ");
        query.append("    ,b.emp_name                      as empName               ");
        query.append("    ,a.courselist_id                 as courseListId         ");
        query.append("    ,(select aa.description                                  ");
        query.append("      from tadept aa                                         ");
        query.append("      where b.comp_no = aa.comp_no                           ");
        query.append("             and b.dept_id = aa.dept_id) as deptName         ");
        query.append("    ,a.train_fdate                       as trainFdate       ");
        query.append("    ,a.train_tdate                       as trainTdate       ");
        query.append("    ,a.train_agency                      as trainAgency      ");
        query.append("    ,a.place                      as place                   ");
        query.append("    ,a.teacher                    as teacher                 ");
        query.append("    ,a.remark                            as remark           ");
        query.append("from TAEMPTRAINLIST a, TAEMP B                               ");
        query.append("where a.comp_no = b.comp_no                                  ");
        query.append("    and a.emp_id =b.emp_id                                   ");
        query.append("    and a.comp_no = '"+loginUser.getCompNo()+"'  ");
        query.append("    and a.emptrainlist_id = "+empTrainListId+" ");

        EduEmpDetailDTO eduEmpDetailDTO =
        		(EduEmpDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new EduEmpDetailDTO()));

        return eduEmpDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: EduEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param eduEmpDetailDTO
     * @param eduCommonDTO
     * @return
     */
    public int updateDetail(EduEmpDetailDTO eduEmpDetailDTO, EduCommonDTO eduCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAEMPTRAINLIST SET	    	");
    	query.append("	 emp_id					= ?	    ");
    	query.append("	,train_fdate			= ?	    ");
    	query.append("	,train_tdate			= ?	    ");
    	query.append("	,train_agency			= ?	    ");
    	query.append("	,place			        = ?	    ");
    	query.append("	,teacher			    = ?	    ");
    	query.append("	,remark					= ?		");
    	query.append("WHERE emptrainlist_id		= ?		");
    	query.append("  AND comp_no			    = ?		");

    	Object[] objects = new Object[] {
    			eduEmpDetailDTO.getEmpId()
    			,eduEmpDetailDTO.getTrainFdate()
    			,eduEmpDetailDTO.getTrainTdate()
    			,eduEmpDetailDTO.getTrainAgency()
    			,eduEmpDetailDTO.getPlace()
    			,eduEmpDetailDTO.getTeacher()
    			,eduEmpDetailDTO.getRemark()
    			,eduEmpDetailDTO.getEmpTrainListId()
    			,loginUser.getCompNo()
    	};

    	return getJdbcTemplate().update(query.toString(), objects);
    }

    /**
     * detail insert
     * @author kim21017
     * @version $Id: EduEmpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param eduEmpDetailDTO
     * @param eduCommonDTO
     * @return
     */
    public int insertDetail(EduEmpDetailDTO eduEmpDetailDTO, EduCommonDTO eduCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAEMPTRAINLIST				");
    	query.append("	(comp_no       ,emptrainlist_id			");
    	query.append("	 ,emp_id       ,courselist_id 			");
    	query.append("	 ,train_fdate     ,train_tdate    		");
    	query.append("	 ,train_agency    ,place    			");
    	query.append("	 ,teacher         ,remark    		    ");
    	query.append("	)	VALUES (						");
    	query.append("	  ?				,?					");
    	query.append("	 ,?				,?					");
    	query.append("	 ,?				,?					");
    	query.append("	 ,?				,?					");
    	query.append("	 ,?				,?					");
    	query.append("	)									");

    	Object[] objects = new Object[] {
    			loginUser.getCompNo()
    			,eduEmpDetailDTO.getEmpTrainListId()
    			,eduEmpDetailDTO.getEmpId()
    			,eduCommonDTO.getCourseListId()
    			,eduEmpDetailDTO.getTrainFdate()
    			,eduEmpDetailDTO.getTrainTdate()
    			,eduEmpDetailDTO.getTrainAgency()
    			,eduEmpDetailDTO.getPlace()
    			,eduEmpDetailDTO.getTeacher()
    			,eduEmpDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * 사원중복확인.
     */
    public String validEmp(EduEmpDetailDTO eduEmpDetailDTO, EduCommonDTO eduCommonDTO, User loginUser){
    	QueryBuffer query = new QueryBuffer();
    	query.append("SELECT count(*) 			");
    	query.append("FROM TAEMPTRAINLIST			");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("courselist_id", eduEmpDetailDTO.getCourseListId());
    	query.getAndQuery("comp_no", loginUser.getCompNo());
    	query.getAndQuery("emp_id", eduEmpDetailDTO.getEmpId());
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
}