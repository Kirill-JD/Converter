<#macro currecy value currencies code>

<div class="col-md">
    <div class="form-floating">
        <select class="form-select" id="floatingSelectGrid" aria-label="Floating label select example" name="<#if value>source<#else>target</#if>">
            <option <#if code??><#else>selected</#if></option>
            <#list currencies as currecy>
            <option value=${currecy.charCode}
                <#if code??>
                    <#if currecy.charCode == code>
                    selected
                    </#if>
                </#if>>${currecy.charCode} (${currecy.name})
            </option>
            </#list>
        </select>
        <label for="floatingSelectGrid">
            <#if value>source currency<#else>target currency</#if>
        </label>
    </div>
</div>

</#macro>