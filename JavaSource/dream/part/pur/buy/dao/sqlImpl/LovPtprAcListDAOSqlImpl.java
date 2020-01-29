package dream.part.pur.buy.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QuerySqlBuffer;
import dream.org.emp.dao.LovEmpListDAO;
import dream.org.emp.dto.LovEmpListDTO;
import dream.part.pur.buy.dao.LovPtprAcListDAO;
import dream.part.pur.buy.dto.LovPtprAcListDTO;

/**
 * 사원검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovPtprAcListDAOTarget"
 * @spring.txbn id="lovPtprAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovPtprAcListDAOSqlImpl extends BaseJdbcDaoSupportOra implements LovPtprAcListDAO
{
    @Override
    public List findTaskMapAcList(LovPtprAcListDTO lovPtprAcListDTO, User user,
            Map<String, String> conditionMap)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT        ");
        query.append("             x.ptprlist_id prprlistId ");
        query.append("            ,x.ptprlist_no ptprlistNo ");
        query.append("            ,z.polist_id polistId ");
        query.append("            ,x.description+'('+y.description+')' description    ");
        query.append("            ,x.ptprlist_status ptprlistStatus ");
        query.append("            ,x.dept_id deptId ");
        query.append("           ,(SELECT description                       ");
        query.append("             FROM TADEPT                              ");
        query.append("            WHERE comp_no = x.comp_no             ");
        query.append("               AND dept_id = x.dept_id) deptDesc      ");
        query.append("            ,x.req_date reqDate   ");
        query.append("            ,y.description itemDesc   ");
        query.append("            ,y.rec_qty recQty     ");
        query.append("            ,y.pt_size spec   ");
        query.append("            ,y.unit_price unitPrice   ");
        query.append("            ,y.part_id partId     ");
        query.append("            ,(SELECT A.part_no    ");
        query.append("              FROM TAPARTS A  ");
        query.append("              WHERE A.part_id = y.part_id ");
        query.append("             ) partNo ");
        query.append("FROM TAPTPOLIST z INNER JOIN TAPTPRITEM y ON z.ptpritem_id = y.ptpritem_id    ");
        query.append("INNER JOIN TAPTPRLIST x ON y.ptprlist_id = x.ptprlist_id  ");

        query.append("WHERE  1 = 1                                  ");

        if(conditionMap.containsKey("comp_no")) {
            query.getAndQuery("comp_no", conditionMap);
        }
        else {
            query.getAndQuery("comp_no", user.getCompNo());
        }
        
        query.getAndQuery("ptprlist_status", conditionMap);
        query.getAndQuery("dept_id", conditionMap);
        
        query.getDeptLevelQuery("dept_id", lovPtprAcListDTO.getDeptId(), lovPtprAcListDTO.getDeptDesc(), user.getCompNo());
        
//        //공장코드
//        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant )", 
//                lovEmpListDTO.getPlantId(), lovEmpListDTO.getPlantDesc());
        
        query.getOrderByQuery("z.polist_id","ptprlist_id DESC", lovPtprAcListDTO.getOrderBy(), lovPtprAcListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovPtprAcListDTO.getIsLoadMaxCount(), lovPtprAcListDTO.getFirstRow()));
        
    }

    @Override
    public String findTotalCount(LovPtprAcListDTO lovPtprAcListDTO, User user,
            Map<String, String> conditionMap)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                        ");
        query.append("       count(1)               ");
        query.append("FROM TAPTPOLIST z INNER JOIN TAPTPRITEM y ON z.ptpritem_id = y.ptpritem_id    ");
        query.append("INNER JOIN TAPTPRLIST x ON y.ptprlist_id = x.ptprlist_id  ");

        query.append("WHERE  1 = 1                                  ");
        if(conditionMap.containsKey("comp_no")) {
            query.getAndQuery("x.comp_no", conditionMap);
        }
        else {
            query.getAndQuery("x.comp_no", user.getCompNo());
        }
        
        query.getAndQuery("ptprlist_status", conditionMap);
        query.getAndQuery("dept_id", conditionMap);
        
        query.getDeptLevelQuery("dept_id", lovPtprAcListDTO.getDeptId(), lovPtprAcListDTO.getDeptDesc(), user.getCompNo());

//        //공장코드
//        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant )", 
//                lovEmpListDTO.getPlantId(), lovEmpListDTO.getPlantDesc());
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
}