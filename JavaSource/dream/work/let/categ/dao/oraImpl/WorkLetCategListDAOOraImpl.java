package dream.work.let.categ.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.let.categ.dao.WorkLetCategListDAO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;

/**
 * 안전작업유형 list Page - List DAO implements
 * @author euna0207
 * @version $Id: WorkLetCategListDAOOraImpl.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workLetCategListDAOTarget"
 * @spring.txbn id="workLetCategListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkLetCategListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkLetCategListDAO
{
	public List findList(WorkLetCategCommonDTO workLetCategCommonDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();
        //AS=컬럼ID
        query.append("SELECT 																									");
        query.append(" ''            		 											          			AS isDelCheck		");
        query.append(",''               													     			AS seqNo			");
        query.append(", comp_no    																  			AS compNo		  	");
        query.append(", woletctg_no    															  			AS woletctgNo  		");
        query.append(", description  														      			AS description		");
        query.append(", SFACODE_TO_DESC(a.woletctg_type,'WOLETCTG_TYPE','SYS','','"+user.getLangId()+"') 			  			AS woLetCtgType		");
        query.append(", (SELECT description FROM TADEPT WHERE comp_no = a.comp_no AND dept_id = a.dept_id)  AS deptId			");
        query.append(", (SELECT emp_name FROM TAEMP WHERE comp_no = a.comp_no AND emp_id = a.emp_id) 		AS empId			");
        query.append(", reg_date                                                                  			AS regDate			");
        query.append(", SFACODE_TO_DESC(a.is_use, 'IS_USE', 'SYS', '', '"+user.getLangId()+"')					 			AS isUse			");
        query.append(", remark                                                                 	  			AS remark			");
        query.append(", woletctg_id                                                               			AS woLetCtgId 		");
        query.append("FROM TAWOLETCTG a																							");
        query.append("WHERE  1=1																								");
    	query.append(this.getWhere(workLetCategCommonDTO, user));	
        query.getOrderByQuery("a.woletctg_id", workLetCategCommonDTO.getOrderBy(), workLetCategCommonDTO.getDirection());
        									//action타고 올때 commonDTO에 셋팅 되어있던 값들을 파라미터로 넣고 정렬
        //Jdbc내의 메소드인 queryForList 실행시켜 select 결과값 리스트로 셋팅.
        //쿼리를 toString으로 불러오고, 그 안에 스크롤시 최대로 몇개까지 카운트할 수 있는지에 대한 함수/스크롤시 첫번째 row 기억하는 함수 담음
        return getJdbcTemplate().queryForList(query.toString(workLetCategCommonDTO.getIsLoadMaxCount(), workLetCategCommonDTO.getFirstRow()));
        } 
	//AND 조건문
	private String getWhere(WorkLetCategCommonDTO workLetCategCommonDTO, User user)
    {        
		//조건문들 넣어주기 위해 queryBuffer 객체 생성
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        
        //수정하거나 생성할때 한줄의 row만 재조회할시
        //key만 가져오므로 전체 조회가 아니고 한줄 조회
        //list.jsp에서 받아온 commonDTO의 key값이 null이 아니면
        if(!"".equals(workLetCategCommonDTO.getWoLetCtgId())){
        	//받아온 key값을 쿼리문의 woletctg_id 컬럼값으로 넣어라
        	query.getAndQuery("a.woletctg_id", workLetCategCommonDTO.getWoLetCtgId());
        	return query.toString();
        }
        //filter의 검색 필드
        //제목
        query.getLikeQuery("a.description", workLetCategCommonDTO.getFilterDes());
        //안전작업유형
    	query.getSysCdQuery("a.woletctg_type", workLetCategCommonDTO.getFilterWoLetCtgTypeId(), workLetCategCommonDTO.getFilterWoLetCtgTypeDesc(), "WOLETCTG_TYPE", user.getCompNo(), user.getLangId());
    	
    	return query.toString();
    }

    public int deleteList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TAWOLETCTG			");
        query.append("WHERE  comp_no 		= ?			");
        query.append("  AND  woletctg_id  	= ?			");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(WorkLetCategCommonDTO workLetCategCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                   ");
        query.append("       COUNT(1)                          ");
        query.append("FROM TAWOLETCTG a						   ");
    	query.append("WHERE  1=1							   ");
    	query.append(this.getWhere(workLetCategCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}