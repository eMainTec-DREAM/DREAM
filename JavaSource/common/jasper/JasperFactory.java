package common.jasper;

import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.chart.AreaChartBuilder;
import net.sf.dynamicreports.report.builder.chart.BarChartBuilder;
import net.sf.dynamicreports.report.builder.chart.LineChartBuilder;
import net.sf.dynamicreports.report.builder.chart.PieChartBuilder;
import net.sf.dynamicreports.report.builder.chart.StackedBar3DChartBuilder;
import net.sf.dynamicreports.report.builder.chart.StackedBarChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;

public class JasperFactory  {

	/** main Title */
	public String mainTitle	= "";
	
	public String subTitle	= "";
	/** List Column Json Str */
	public String jsonColumns	= "";
	
	public String hiperText	= "";
	
	public String logoImgName	= "";
	
	public List reportList	 = null; 
	
	public List<Map> colList	= null;
	
	public String chartType = "";
	
	public boolean percentages = false;
	
	public boolean showValue = false;
	
	public boolean showLabel = false;
	
	public String percentValuePatter = "#,##0";

	public String[] serie = null;
	
	public String category = "";
	
	public Map<String, TextColumnBuilder<?>> columns = null;

	JasperReportBuilder rptBuiler = null;
	Templates templates = null;
	
	
	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		//rptBuiler.title(templates.createTitleComponent(mainTitle));
		this.mainTitle = mainTitle;
	}

	public String getJsonColumns() {
		return jsonColumns;
	}

	public void setJsonColumns(String jsonColumns) {
		this.jsonColumns = jsonColumns;
	}

	public String getHiperText() {
		return hiperText;
	}

	public void setHiperText(String hiperText) {
		this.hiperText = hiperText;
	}

	public String getLogoImgName() {
		return logoImgName;
	}

	public void setLogoImgName(String logoImgName) {
		this.logoImgName = logoImgName;
	}
	
	public void setShowLabel(boolean pShowLabel){
		this.showLabel = pShowLabel;
	}

	public JasperFactory() {
		super();

	}
	
	public JasperFactory(String compTitle) {
		super();
		
		templates = new Templates(compTitle);
		rptBuiler = report()
		.setTemplate(templates.reportTemplate);
	}
	
	public JasperFactory(String comTitle, String logoImgUrl) {
		super();
		
		templates = new Templates(comTitle, logoImgUrl);
		rptBuiler = report()
		.setTemplate(templates.reportTemplate);
	}
	
	public void setResultList(List resultList) throws JRException
	{
		this.reportList = resultList;
	}
	
	public void setColList(List colList)
	{
		this.colList = colList;
	}
	
	public void setChartType(String pChartType)
	{
		this.chartType = pChartType;
	}
	
	public void setPercentages(boolean pPercentages)
	{
		this.percentages = pPercentages;
	}
	
	public void setShowValues(boolean pShowValue)
	{
		this.showValue = pShowValue;
	}
	
	public void setPercentValuePattern(String pPattern)
	{
		this.percentValuePatter = pPattern;
	}
	
	public void setSerie(String fSerie, String... pSerie)
	{
		int i = 1;
		serie = new String[pSerie.length+1];
		serie[0] = fSerie;
		for(String ser : pSerie)
		{
			this.serie[i] = ser;
		}
//		this.serie = pSerie;
	}
	
	public void setCategory(String pCategory)
	{
		this.category = pCategory;
	}
	
	public int titleFontSize = 22;
	
	public int subTitleFontSize = 17;
	
	public int colHeaderFontSize = 20;
	
	public int colFontSize = 20;
	
	public String titleFont = "Monospaced";
	
	public String subTitleFont = "Monospaced";
	
	public String colHeaderFont = "Monospaced";
	
	public String colFont	= "Monospaced";
	
	public boolean titleBold	= true;
	
	public boolean subTitleBold	= false;
	
	public boolean colHeaderBold = true;
	
	public boolean colBold	= false;
	
	public HorizontalTextAlignment titleAlign = HorizontalTextAlignment.LEFT;
	
	public HorizontalTextAlignment subTitleAlign = HorizontalTextAlignment.CENTER;
	
	public HorizontalTextAlignment colHeaderAlign = HorizontalTextAlignment.CENTER;
	
	public HorizontalTextAlignment colAlign = HorizontalTextAlignment.LEFT;
	
	public void view() throws DRException, JRException
	{
		//View에서 subreport로 리포트 만들고 
		// mainreport에 타이틀, 서브타이틀 입력하고 (위치, 크기, 폰트 등 옵션지정)
		//subreprot 에 column 설정하고 (크기, 폰트 등등 )
		//설정된 위치에 따라 chart와 subreport를 summary에 위치시키다. (Vertical)
		//끝!
		
		rptBuiler = report();
		rptBuiler.setVirtualizer(new JRFileVirtualizer(2));
		
		StyleBuilder titleStyle = stl.style().setBold(titleBold).setFontSize(titleFontSize).setFontName(titleFont).setTopPadding(5);
		StyleBuilder subTitleStyle = stl.style().setBold(subTitleBold).setFontSize(subTitleFontSize).setFontName(subTitleFont);
		StyleBuilder columnHeaderStyle = stl.style().setBold(colHeaderBold).setFontSize(colHeaderFontSize).setFontName(colHeaderFont).setPadding(2);
		StyleBuilder columnStyle = stl.style().setBold(colBold).setFontSize(colFontSize).setFontName(colFont);
		
		rptBuiler.title(
				cmp.verticalList(
						cmp.horizontalFlowList(
								cmp.image(logoImgName).setFixedDimension(60, 60),
								cmp.horizontalGap(5),
								cmp.centerVertical(
										cmp.text(mainTitle).setStyle(titleStyle)
										.setHorizontalTextAlignment(titleAlign)
								)
						)
				,cmp.verticalGap(5)
				,cmp.text(subTitle).setStyle(subTitleStyle).setHorizontalTextAlignment(subTitleAlign)
				,cmp.line()
				,cmp.verticalGap(10)
				)
				);
		
		columns = new HashMap<String, TextColumnBuilder<?>>();
		
		String[] serieStr = null; 
		if(this.serie != null) serieStr = serie;
		
		JasperReportBuilder subreport = report();
		subreport.setVirtualizer(new JRFileVirtualizer(2));
		
		if(colList.size() > 0)
        {
            TextColumnBuilder<Integer> intCol = null;
            TextColumnBuilder<String> strCol = null;
            
			for(Map colMap: colList)
			{
				String colKey 	= colMap.get("key")+"."+colMap.get("columnName");
				String colAlias = String.valueOf(colMap.get("colAlias"));
				int colFontSize = ((BigDecimal)colMap.get("colSize")).intValue();
				int colWidth = ((BigDecimal)colMap.get("colWidth")).intValue();
				HorizontalTextAlignment colHalign = "center".equals(colMap.get("colAlign"))?HorizontalTextAlignment.CENTER:"left".equals(colMap.get("colAlign"))?HorizontalTextAlignment.LEFT:HorizontalTextAlignment.RIGHT;
				
				columnHeaderStyle = stl.style().setFontSize(colFontSize)
						.setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY)// .getHSBColor(102,49,76))// Color.lightGray)
						.setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE)
						.setBold(colHeaderBold).setPadding(2);

				columnStyle = stl.style().setFontSize(colFontSize)
        				.setBorder(stl.penThin())//.setBackgroundColor(Color.lightGray)
        				.setTextAlignment(colHalign, VerticalTextAlignment.MIDDLE);
				
				
				strCol = col.column(colAlias, colAlias, type.stringType())
						.setTitleStyle(columnHeaderStyle).setWidth(colWidth)
        				.setStyle(
        						stl.style(columnStyle)
        						.setPadding(2)
        						);

        		columns.put(colAlias, strCol);
        		subreport.addColumn(strCol);
        		
			}
        }
		
        Gson gson = new Gson();
        String jsonStr = gson.toJson(reportList);

        InputStream iostream = new ByteArrayInputStream(jsonStr.getBytes(StandardCharsets.UTF_8));
        InputStream subIostream = new ByteArrayInputStream(jsonStr.getBytes(StandardCharsets.UTF_8));
 		rptBuiler.setDataSource(new JsonDataSource(iostream));
 		subreport.setDataSource(new JsonDataSource(subIostream));
 		
 		rptBuiler.summary(
 				cmp.verticalList(cmp.verticalList(
						cmp.subreport(subreport)
					)
 						//getHorizontal(subreport)
 						)
 				);
 		
		rptBuiler.show(false);
	}
	
	public VerticalListBuilder getHorizontal(JasperReportBuilder subreport)
	{
		//if(this.chartType == "") return null;
		
		BarChartBuilder barChart = null;
		StackedBarChartBuilder stackedBarChart = null;
		LineChartBuilder lineChart = null;
		AreaChartBuilder areaChart = null;
		StackedBar3DChartBuilder stackedBar3DChart = null;
		PieChartBuilder pieChart = null;
		
		switch (chartType)
        {
            case "barChart":
            	barChart = cht.barChart()
  		      	.setShowPercentages(percentages)
  		      	.setShowValues(showValue)
  		      	.setPercentValuePattern(percentValuePatter)
  		      	.setShowLabels(showLabel);
                break;
            case "stackedBarChart":
            	stackedBarChart = cht.stackedBarChart()
				.setShowPercentages(percentages)
				.setShowValues(showValue)
				.setValuePattern(percentValuePatter)
				.setShowLabels(showLabel);
                break;
            case "lineChart":
            	lineChart = cht.lineChart()
			      .setShowPercentages(percentages)
			      .setShowValues(showValue)
			      .setPercentValuePattern(percentValuePatter);
                break;
            case "areaChart":
            	areaChart = cht.areaChart()
			      .setShowPercentages(percentages)
			      .setShowValues(showValue)
			      .setPercentValuePattern(percentValuePatter);
                break;
            case "stackedBar3DChart":
            	stackedBar3DChart = cht.stackedBar3DChart()
			      .setShowPercentages(percentages)
			      .setShowValues(showValue)
			      .setPercentValuePattern(percentValuePatter)
			      .setShowLabels(showLabel);
                break;
            case "pieChart":
            	pieChart = cht.pieChart()
			      .setShowPercentages(percentages)
			      .setShowValues(showValue)
			      .setPercentValuePattern(percentValuePatter)
			      .setShowLabels(showLabel);
                break;
            default:
            	barChart = cht.barChart()
  		      	.setShowPercentages(percentages)
  		      	.setShowValues(showValue)
  		      	.setPercentValuePattern(percentValuePatter);
                break;
        }

		String[] serieStr = null; 
		if(this.serie != null) serieStr = serie;
		
		for( String key : columns.keySet() ){
			boolean isMatch = false;
        	for(String ser : serieStr) //숫자형식만 들어가야 함
        	{
        		if(ser.toUpperCase().equals(key.toUpperCase())) isMatch = true;
        	}

        	if(isMatch)
        	{
        		if(barChart != null) barChart.addSerie(cht.serie((TextColumnBuilder<Integer>)columns.get(key)));
        		if(stackedBarChart != null) stackedBarChart.addSerie(cht.serie((TextColumnBuilder<Integer>)columns.get(key)));
        		if(lineChart != null) lineChart.addSerie(cht.serie((TextColumnBuilder<Integer>)columns.get(key)));
        		if(areaChart != null) areaChart.addSerie(cht.serie((TextColumnBuilder<Integer>)columns.get(key)));
        		if(stackedBar3DChart != null) stackedBar3DChart.addSerie(cht.serie((TextColumnBuilder<Integer>)columns.get(key)));
        		if(pieChart != null) pieChart.addSerie(cht.serie((TextColumnBuilder<Integer>)columns.get(key)));
        	}
        }
		
		if(barChart != null)barChart.setCategory((TextColumnBuilder<String>)columns.get(category));
		if(stackedBarChart != null)stackedBarChart.setCategory((TextColumnBuilder<String>)columns.get(category));
		if(lineChart != null)lineChart.setCategory((TextColumnBuilder<String>)columns.get(category));
		if(areaChart != null)areaChart.setCategory((TextColumnBuilder<String>)columns.get(category));
		if(stackedBar3DChart != null)stackedBar3DChart.setCategory((TextColumnBuilder<String>)columns.get(category));
		if(pieChart != null)pieChart.setKey((TextColumnBuilder<String>)columns.get(category));
		

		
		//HorizontalListBuilder
		VerticalListBuilder cmpBuilder = null;
		if(this.chartType == "")
		{
			cmpBuilder = cmp.verticalList(
						cmp.subreport(subreport)
					);
		}
		else
		{
			cmpBuilder = cmp.verticalList(
					
					barChart==null?
							stackedBarChart==null?
									lineChart==null?
											areaChart==null?
													stackedBar3DChart==null?
															pieChart
															:stackedBar3DChart
															:areaChart
															:lineChart
															:stackedBarChart
															:barChart
															,cmp.verticalGap(30)
															,cmp.subreport(subreport)
					);
		}
		return cmpBuilder;
	}
}
