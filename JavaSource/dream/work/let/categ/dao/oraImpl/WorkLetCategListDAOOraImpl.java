package dream.work.let.categ.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.let.categ.dao.WorkLetCategListDAO;
import dream.work.let.categ.dto.WorkLetCategCommonDTO;

/**
 * �����۾����� list Page - List DAO implements
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
        //AS=�÷�ID
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
        									//actionŸ�� �ö� commonDTO�� ���� �Ǿ��ִ� ������ �Ķ���ͷ� �ְ� ����
        //Jdbc���� �޼ҵ��� queryForList ������� select ����� ����Ʈ�� ����.
        //������ toString���� �ҷ�����, �� �ȿ� ��ũ�ѽ� �ִ�� ����� ī��Ʈ�� �� �ִ����� ���� �Լ�/��ũ�ѽ� ù��° row ����ϴ� �Լ� ����
        return getJdbcTemplate().queryForList(query.toString(workLetCategCommonDTO.getIsLoadMaxCount(), workLetCategCommonDTO.getFirstRow()));
        } 
	//AND ���ǹ�
	private String getWhere(WorkLetCategCommonDTO workLetCategCommonDTO, User user)
    {        
		//���ǹ��� �־��ֱ� ���� queryBuffer ��ü ����
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("a.comp_no", user.getCompNo());
        
        //�����ϰų� �����Ҷ� ������ row�� ����ȸ�ҽ�
        //key�� �������Ƿ� ��ü ��ȸ�� �ƴϰ� ���� ��ȸ
        //list.jsp���� �޾ƿ� commonDTO�� key���� null�� �ƴϸ�
        if(!"".equals(workLetCategCommonDTO.getWoLetCtgId())){
        	//�޾ƿ� key���� �������� woletctg_id �÷������� �־��
        	query.getAndQuery("a.woletctg_id", workLetCategCommonDTO.getWoLetCtgId());
        	return query.toString();
        }
        //filter�� �˻� �ʵ�
        //����
        query.getLikeQuery("a.description", workLetCategCommonDTO.getFilterDes());
        //�����۾�����
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