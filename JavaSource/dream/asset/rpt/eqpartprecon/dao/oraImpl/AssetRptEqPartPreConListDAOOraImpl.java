package dream.asset.rpt.eqpartprecon.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.rpt.eqpartprecon.dao.AssetRptEqPartPreConListDAO;
import dream.asset.rpt.eqpartprecon.dto.AssetRptEqPartPreConCommonDTO;

/**
 * AssetRptEqPartPreCon Page - List DAO implements
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="assetRptEqPartPreConListDAOTarget"
 * @spring.txbn id="assetRptEqPartPreConListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptEqPartPreConListDAOOraImpl  extends BaseJdbcDaoSupportOra implements AssetRptEqPartPreConListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptEqPartPreConCommonDTO
     * @return List
     */
    public List findList(AssetRptEqPartPreConCommonDTO assetRptEqPartPreConCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                                                           ");
        query.append("      ''                                          seqNo          ");
        query.append("      ,''                                         isDelCheck     ");
        query.append("      ,x.equip_id                                 equipId        ");
        query.append("      ,x.item_no                                  itemNo         ");
        query.append("      ,CASE x.eqctg_type WHEN 'MD' THEN '('||NVL(x.old_eq_no,'')||')'||x.description ELSE x.description END equipDesc     ");
        query.append("      ,(SELECT full_desc                                         ");
        query.append("         FROM TAEQLOC                                            ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND eqloc_id = x.eqloc_id)              eqLocDesc      ");
        query.append("      ,(SELECT full_desc                                         ");
        query.append("         FROM TAEQCTG                                            ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND eqctg_id = x.eqctg_id)              eqCtgDesc      ");
        query.append("      ,x.maker                                    maker          ");
        query.append("      ,x.model_no                                 modelNo        ");
        query.append("      ,SFACODE_TO_DESC(x.plf_type,'PLF_TYPE','SYS','','"+user.getLangId()+"')  plfTypeDesc");
        query.append("      ,x.eqctg_type                               eqCtgType      ");
        query.append("      ,SFACODE_TO_DESC(x.eqctg_type,'EQCTG_TYPE','SYS','','"+user.getLangId()+"')  eqCtgTypeDesc");
        query.append("      ,x.is_law_eq                                isLawEq        ");
        query.append("      ,(SELECT description                                       ");
        query.append("         FROM TADEPT                                             ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND dept_id = x.dept_id)                deptDesc       ");
        query.append("      ,(SELECT emp_name                                          ");
        query.append("         FROM TAEMP                                              ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND emp_id = x.main_mng_id)             mainMngName    ");
        query.append("      ,(SELECT emp_name                                          ");
        query.append("         FROM TAEMP                                              ");
        query.append("        WHERE comp_no = x.comp_no                                ");
        query.append("          AND emp_id = x.sub_mng_id)              subMngName     ");
        query.append("      ,x.dept_id                                  deptId         ");
        query.append("      ,x.excel_no                                 excelNo        ");
        query.append("      ,SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+user.getLangId()+"')	eqstatus    ");
        query.append("      ,(select param1 from tacdsysd where list_type='EQCTG_TYPE' AND cdsysd_no = x.eqctg_type) param");
        query.append("      ,SFACODE_TO_DESC(x.revision_status,'REVISION_STATUS','SYS','','"+user.getLangId()+"')    revisionStatus    ");
        query.append("      ,x.is_last_version                                  isLastVersion        ");
        query.append("      ,x.prod_shape                               prodShape    ");
        query.append("      ,x.old_eq_no                               oldEqNo    ");
        query.append("      ,x.eqstrloc_no       eqStrLocNo       ");
        query.append("      ,SFACODE_TO_DESC(x.eqstrloc_no, 'EQSTRLOC_NO', 'USR', x.comp_no, '"+user.getLangId()+"') eqStrLocDesc       ");
        query.append("      ,x.setup_date        setupDate        ");
        query.append("      ,x.capacity            capacity       ");
        query.append("      ,x.serial_no           serialNo       ");
        query.append("      ,x.supplier            supplier       ");
        query.append("      ,x.country_maker     countryMaker     ");
        query.append("      ,x.util_capa             utilCapa     ");
        query.append("		,x.eq_grade            eqGradeDesc    ");
        query.append("FROM  TAEQUIPMENT x                                              ");
        query.append("WHERE 1 = 1                                                      ");
        query.append(this.getWhere(assetRptEqPartPreConCommonDTO, user));
        
        query.getOrderByQuery("x.description", assetRptEqPartPreConCommonDTO.getOrderBy(), assetRptEqPartPreConCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptEqPartPreConCommonDTO.getIsLoadMaxCount(), assetRptEqPartPreConCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     *   
     * @param assetRptEqPartPreConCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(AssetRptEqPartPreConCommonDTO assetRptEqPartPreConCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.eqctg_type", "MC");
        query.getAndQuery("x.is_deleted", "N");
        
        // 공장
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		assetRptEqPartPreConCommonDTO.getFilterPlantId(), assetRptEqPartPreConCommonDTO.getFilterPlantDesc());
       
        //위치
        query.getEqLocLevelQuery("x.eqloc_id", assetRptEqPartPreConCommonDTO.getFilterEqLocId(), assetRptEqPartPreConCommonDTO.getFilterEqLocDesc(), user.getCompNo());

        //종류
        query.getEqCtgLevelQuery("x.eqctg_id", assetRptEqPartPreConCommonDTO.getFilterEqCtgId(), assetRptEqPartPreConCommonDTO.getFilterEqCtgDesc(), user.getCompNo());
        
        //설비명
        query.getLikeQuery("x.description", assetRptEqPartPreConCommonDTO.getFilterEquipDesc());

        return query.toString();
    }

    public String findTotalCount(
            AssetRptEqPartPreConCommonDTO assetRptEqPartPreConCommonDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT					");
    	query.append("		count(1)			");
    	query.append("FROM   TAEQUIPMENT x		");
    	query.append("WHERE  1 = 1              ");
    	query.append(this.getWhere(assetRptEqPartPreConCommonDTO, user));

    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
