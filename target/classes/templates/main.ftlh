<#import "parts/common.ftlh" as c>
<#import "parts/currency.ftlh" as cur>

<@c.page>

        <form method="post" action="/main">
            <div class="row gy-5 mt-1">
                <div class="row g-2">
                    <@cur.currecy true currencies source />
                    <div class="col-md">
                        <div class="form-floating">
                            <input type="number" class="form-control" id="floatingInputGrid" name="amount" value=<#if amount??>${amount}<#else>0</#if>>
                            <label for="floatingInputGrid">the amount</label>
                        </div>
                    </div>
                </div>
                <div class="row g-2">
                    <@cur.currecy false currencies target />
                    <div class="col">
                        <div class="p-3 border bg-light">
                            <#if result??>${result}<#else>enter the amount</#if>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-12">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-primary mt-3">Convert</button>
            </div>
        </form>

</@c.page>