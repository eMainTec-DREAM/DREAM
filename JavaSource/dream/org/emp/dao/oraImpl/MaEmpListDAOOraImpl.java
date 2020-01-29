package dream.org.emp.dao.oraImpl;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.org.emp.dao.MaEmpListDAO;
import dream.org.emp.dto.MaEmpCommonDTO;

/**
 * 사원 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maEmpListDAOTarget"
 * @spring.txbn id="maEmpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEmpListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEmpListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEmpCommonDTO
     * @return List
     * @throws IOException 
     */
    public List findEmpList(MaEmpCommonDTO maEmpCommonDTO, User user) throws IOException
    { 
        QueryBuffer query = new QueryBuffer(); 

        query.append("SELECT ''                                     seqNo       ");
        query.append("      ,''                                     isDelCheck  ");
        query.append("      ,x.comp_no                              compNo      ");
        query.append("      ,x.emp_id                               empId       "); 
        query.append("      ,x.emp_no                               empNo       "); 
        query.append("      ,x.emp_name                             empName     "); 
        query.append("      ,x.dept_id                              deptId      ");
        query.append("      ,SFAIDTODESC(x.dept_id, '', 'DEPT', x.comp_no)     deptDesc  "); 
        query.append("      ,x.wkctr_id                             wkCtrId     ");
        query.append("		,(SELECT description								");
        query.append("		 FROM TAWKCTR										");
        query.append("		 WHERE comp_no = x.comp_no							");
        query.append("		 AND wkctr_id = x.wkctr_id)             wkCtrDesc	");
        query.append("      ,x.grade                                grade       "); 
        query.append("      ,x.m_phone                              mphone      "); 
        query.append("      ,x.e_mail                               email       "); 
        query.append("      ,x.join_date                            joinDate    ");
        query.append("      ,x.retire_date                          retireDate  ");
        query.append("      ,x.is_join                              isJoin      ");
        query.append("      ,SFACODE_TO_DESC(x.is_join, 'IS_JOIN', 'SYS', x.comp_no,'"+user.getLangId()+"') isJoinDesc"); 
        query.append("      ,x.is_mail_rec                          isMailRec   ");
        query.append("      ,SFACODE_TO_DESC(x.is_mail_rec, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId()+"') isMailRecDesc ");
        query.append("      ,x.plant                                plantId     ");
        query.append("      ,(select description								");
        query.append("       from taplant										");
        query.append("       where comp_no = x.comp_no							");
        query.append("       and plant = x.plant)        			plantDesc	");
        query.append("      ,(select dept_no									");
        query.append("       from tadept										");
        query.append("       where comp_no = x.comp_no							");
        query.append("       and dept_id = x.dept_id)    			deptNo		");
        query.append("      ,x.vendor_id                            vendorId    ");
        query.append("      ,(SELECT description								");
        query.append("       FROM tavendor										");
        query.append("       WHERE comp_no = x.comp_no							");
        query.append("       AND vendor_id = x.VENDOR_ID)    		vendorDesc	");
        query.append("      ,x.is_direct                            isDirect    ");
        query.append("      ,SFACODE_TO_DESC(x.is_direct, 'IS_USE', 'SYS', x.comp_no,'"+user.getLangId() + "')     isDirectDesc    		");
        query.append("FROM   TAEMP x                                            ");
        query.append("WHERE  1=1                                                "); 
        query.append(this.getWhere(maEmpCommonDTO, user));
        query.getOrderByQuery("x.emp_id", maEmpCommonDTO.getOrderBy(), maEmpCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maEmpCommonDTO.getIsLoadMaxCount(), maEmpCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maEmpCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaEmpCommonDTO maEmpCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        // CommonDTO의 equipNo가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(maEmpCommonDTO.getEmpId()))
        {
            query.getAndQuery("x.emp_id", maEmpCommonDTO.getEmpId());
            return query.toString();
        }
        
        query.getLikeQuery("x.emp_no", maEmpCommonDTO.getFilterEmpNo());
        query.getLikeQuery("x.emp_name", maEmpCommonDTO.getFilterEmpName());

        query.getDeptLevelQuery("x.dept_id", maEmpCommonDTO.getFilterDeptId(), maEmpCommonDTO.getFilterDeptDesc(), maEmpCommonDTO.getCompNo());
        
        query.getWkCtrLevelQuery("x.wkctr_id", maEmpCommonDTO.getFilterWkCtrId(), maEmpCommonDTO.getFilterWkCtrDesc(), maEmpCommonDTO.getCompNo());
        
        // 근무여부 
        query.getSysCdQuery("x.is_join", maEmpCommonDTO.getFilterIsJoin(), maEmpCommonDTO.getFilterIsJoinDesc(), "IS_JOIN", maEmpCommonDTO.getCompNo(),user.getLangId());

        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		maEmpCommonDTO.getFilterPlantId(), maEmpCommonDTO.getFilterPlantDesc());

        // 직영여부
        query.getAndQuery("x.is_direct", maEmpCommonDTO.getFilterIsDirectDesc());
        
        return query.toString();
    }

    /**
     * 삭제
     * @author ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param empId
     * @return
     */
    public int deleteEmp(String compNo, String empId)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAEMP                             ");
        query.append("WHERE  comp_no  = ?                      ");
        query.append("  AND  emp_id   = ?                      ");

        Object[] objects = new Object[] {   
                compNo,
                empId
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

    @Override
    public String findTotalCount(MaEmpCommonDTO maEmpCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                    ");
        query.append("      count(1)            ");
        query.append("FROM   TAEMP x                                              ");
        query.append("WHERE  1 = 1                                                      ");
        query.append(this.getWhere(maEmpCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}