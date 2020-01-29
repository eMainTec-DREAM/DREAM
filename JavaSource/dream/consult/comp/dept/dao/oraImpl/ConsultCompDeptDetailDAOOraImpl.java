package dream.consult.comp.dept.dao.oraImpl;




import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.consult.comp.dept.dao.ConsultCompDeptDetailDAO;
import dream.consult.comp.dept.dto.ConsultCompDeptCommonDTO;
import dream.consult.comp.dept.dto.ConsultCompDeptDetailDTO;


/**
 * 보전부서 - 상세 dao
 * 
 * @author hyosung
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="consultCompDeptDetailDAOTarget"
 * @spring.txbn id="consultCompDeptDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ConsultCompDeptDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements ConsultCompDeptDetailDAO
{
    /**
     * detail find
     * @author  hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param deptNo
     * @return
     */
    public ConsultCompDeptDetailDTO findDetail(ConsultCompDeptCommonDTO consultCompDeptCommonDTO,String langId)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT               ");
        query.append("     x.comp_no     compNo                                                           ");
        query.append("    ,x.dept_id     deptId                                                           ");
        query.append("    ,(select xx.description from TACOMP xx where x.comp_no=xx.comp_no ) compDesc    ");
        query.append("    ,x.dept_no     deptNo                                                           ");
        query.append("    ,x.description  description                                                     ");
        query.append("    ,x.p_dept_id    pdeptId                                                         ");
        query.append("    ,SFAIDTODESC(x.p_dept_id, '', 'DEPT', x.comp_no) pdeptDesc                      ");
        query.append("    ,x.ord_no       ordNo                                                           ");
        query.append("    ,x.is_use       isUse                                                           ");
        query.append("    ,SFACODE_TO_DESC(x.is_use, 'IS_USE', 'SYS', x.comp_no,'"+langId+"') isUseDesc   ");
        query.append("    ,x.is_maint       isMaint                                                           ");
        query.append("    ,SFACODE_TO_DESC(x.is_maint, 'IS_USE', 'SYS', x.comp_no,'"+langId+"') isMaintDesc   ");
        query.append("FROM TADEPT x                                                                       ");
        query.append("WHERE x.dept_id='"+consultCompDeptCommonDTO.getDeptId()+"'                          ");
        query.append("  AND x.comp_no='"+consultCompDeptCommonDTO.getFilterCompNo()+"'                    ");
        
        
        ConsultCompDeptDetailDTO consultCompDeptDetailDTO = 
                (ConsultCompDeptDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new ConsultCompDeptDetailDTO()));


        return consultCompDeptDetailDTO;
    }

    /**
     * detail insert
     * @author  hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompDeptDetailDTO
     * @return
     */
    public int insertDetail(ConsultCompDeptDetailDTO consultCompDeptDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query.append("INSERT INTO TADEPT(                                                     ");
        query.append("        comp_no                                                         ");
        query.append("        ,dept_id                                                        ");
        query.append("        ,dept_no                                                        ");
        query.append("        ,description                                                    ");
        query.append("        ,p_dept_id                                                      ");
        query.append("        ,is_use                                                         ");
        query.append("        ,is_maint                                                         ");
        query.append("        ,ord_no                                                         ");
        query.append(")VALUES(                                                                ");
        query.append("        ?                                                               ");
        query.append("        ,?                                                              ");
        query.append("        ,?                                                              ");
        query.append("        ,?                                                              ");
        query.append("        ,NVL( ? , 0 )                                                   ");
        query.append("        ,?                                                              ");
        query.append("        ,?                                                              ");
        query.append("        ,?                                                              ");
        query.append(")                                                                       ");
        
         Object[] objects = new Object[] {
                
                consultCompDeptDetailDTO.getCompNo()
                ,consultCompDeptDetailDTO.getDeptId()
                ,consultCompDeptDetailDTO.getDeptNo()
                ,consultCompDeptDetailDTO.getDescription()
                ,consultCompDeptDetailDTO.getPdeptId()
                ,consultCompDeptDetailDTO.getIsUse()
                ,consultCompDeptDetailDTO.getIsMaint()
                ,consultCompDeptDetailDTO.getOrdNo()

        };

        rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }

    /**
     * detail update
     * @author hyosung
     * @version $Id: $
     * @since   1.0
     * 
     * @param consultCompDeptDetailDTO
     * @return
     */
    public int updateDetail(ConsultCompDeptDetailDTO consultCompDeptDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();
  
        int rtnValue  = 0;
            
        query.append("UPDATE TADEPT SET	                            ");
        query.append("	     description    = ?	                    ");
        query.append("	     ,p_dept_id     = NVL(?, 0)	            ");
        query.append("	     ,is_use	    = ?	                    ");
        query.append("       ,is_maint        = ?                     ");
        query.append("	     ,ord_no        = ?		                ");
        query.append("WHERE  comp_no        = ?	                    ");
        query.append("  AND  dept_id        = ?	                    ");

        Object[] objects = new Object[] {
                consultCompDeptDetailDTO.getDescription()
                ,consultCompDeptDetailDTO.getPdeptId()
                ,consultCompDeptDetailDTO.getIsUse()
                ,consultCompDeptDetailDTO.getIsMaint()
                ,consultCompDeptDetailDTO.getOrdNo()
                ,consultCompDeptDetailDTO.getCompNo()
                ,consultCompDeptDetailDTO.getDeptId()
        };

        rtnValue =  getJdbcTemplate().update(query.toString(), objects);

        
        return rtnValue;
    }

    /**
     * valid deptNo
     * @author hyosung
     * @version $Id:  $
     * @since   1.0
     * 
     * @param consultCompDeptDetailDTO
     * @return
     */
    public String validDeptNo(ConsultCompDeptDetailDTO consultCompDeptDetailDTO)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT COUNT(*)                                                  ");
        query.append("FROM   TADEPT x                                                  ");
        query.append("WHERE  x.comp_no = '" + consultCompDeptDetailDTO.getCompNo() + "'");
        query.append("  AND  x.dept_no = '" + consultCompDeptDetailDTO.getDeptNo() + "'");

        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
}