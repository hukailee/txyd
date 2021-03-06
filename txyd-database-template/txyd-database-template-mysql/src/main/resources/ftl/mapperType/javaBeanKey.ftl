<#if tableBean.primaryKeyNum gt 1 >
<#-- 类文件的package模版 -->
package ${jcb.basePackageModel?lower_case};

<#-- 类文件的import模版 -->
import java.io.Serializable;
<#list importSet as import >
import ${import} ;
</#list>
<#-- 类文件的注解import模版-->

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
<#-- 类文件的class注释模版-->
/**		
 * 数据库类型：${tableBean.databaseType}
 * 表所属schema：${tableBean.tableSchema}
 * 表所属用户：${tableBean.tableOwner}
 * 表名称：${tableBean.tableName}
 * 表注释：${tableBean.comments}
 * 类型：${tableBean.tableType}
 * @author：${jcb.author}
 */
<#-- 类文件的class注解模版-->
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ${tableBean.javabeanKeyClassName} implements Serializable  {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
<#-- 类文件的属性模版-->
<#list columnBeanList as columnBean>
	<#if columnBean.isPrimaryKey >
	
	/**
	 * 是否可以为NULL：${columnBean.isNullAble?string('是','否')}
	 * 列类型：${columnBean.columnType}
	 * 默认值：${columnBean.defaultValue!'null'}
	 * 列的数据类型的长度：${columnBean.dataLength}
	 * 列注释：${StringUtil.newLine2Html(columnBean.comments)}
	 * 列的扩展：${columnBean.extra}
	 * 列名：${columnBean.columnName}
	 * 列的数据类型：${columnBean.dataType}
	 * 是否是主键：${columnBean.isPrimaryKey?string('是','否')}
	 */
	@JsonProperty("${columnBean.columnName}")
	private ${columnBean.javabeanFieldDataTypeSimple} ${columnBean.javabeanFieldName};	
	</#if>
</#list>


<#-- 类文件的方法模版-->
<#list columnBeanList as columnBean>
	<#if columnBean.isPrimaryKey >
	public ${columnBean.javabeanFieldDataTypeSimple} get${StringUtil.toUpperCaseOfFirstChar(columnBean.javabeanFieldName)}(){
		return this.${columnBean.javabeanFieldName};
	}
	
	public void set${StringUtil.toUpperCaseOfFirstChar(columnBean.javabeanFieldName)}(${columnBean.javabeanFieldDataTypeSimple} ${columnBean.javabeanFieldName}){
		this.${columnBean.javabeanFieldName} = ${columnBean.javabeanFieldName};
	}
	
	</#if>
</#list>

<#-- 深度复制
	/**
	* 深度复制
	*/
	public  ${tableBean.javabeanKeyClassName} deepClone() {
		${tableBean.javabeanKeyClassName} entity = new ${tableBean.javabeanKeyClassName}();
<#list columnBeanKeyList as columnBean >
		entity.set${StringUtil.getJavabeanFieldNameOfSetGetMethod(columnBean.javabeanFieldName)}(this.get${StringUtil.getJavabeanFieldNameOfSetGetMethod(columnBean.javabeanFieldName)}());
</#list>
		return entity;
	}

	@Override
	public String toString() {
		return "${tableBean.javabeanModelClassName}{"
<#list columnBeanKeyList as columnBean >
	<#if columnBean_has_next>
		<#if columnBean.javabeanFieldDataTypeIsNum>
				+ " \"${columnBean.javabeanFieldName}\":" + ${columnBean.javabeanFieldName} +","
		<#else>
				+ " \"${columnBean.javabeanFieldName}\":\"" + ${columnBean.javabeanFieldName} +"\","
		</#if>
	<#else>
		<#if columnBean.javabeanFieldDataTypeIsNum>
				+ " \"${columnBean.javabeanFieldName}\":" + ${columnBean.javabeanFieldName} +""
		<#else>
				+ " \"${columnBean.javabeanFieldName}\":\"" + ${columnBean.javabeanFieldName} +"\""
		</#if>
	</#if>
</#list>
		+"}";
	}
-->
}
</#if>