<#import "parts/common.ftlh" as c>
<#import "parts/currency.ftlh" as cur>

<@c.page>
    <div class="col-md mt-3"></div>
        <div class="row">
            <form method="get" action="/history" class="row row-cols-lg-auto g-3 align-items-center">
                <div class="col">
                    <div class="form-floating">
                        <input type="date" class="form-control" placeholder="Date" aria-label="Date" name="date" value="${date?ifExists}">
                    </div>
                </div>
                <div class="col">
                    <@cur.currecy true currencies source />
                </div>
                <div class="col">
                    <@cur.currecy false currencies target />
                </div>
                <div class="d-grid gap-2 col-6 mx-auto">
                    <button class="btn btn-primary" type="submit">Search</button>
                </div>
            </form>
        </div>

        <div class="row gy-5 mt-1">
            <table class="table table-success table-striped">
                <tr>
                    <th>source currency</th>
                    <th>target currency</th>
                    <th>initial amount</th>
                    <th>amount received</th>
                    <th>date</th>
                </tr>
                <#list histories as history>
                    <#if user.id == history.user.id>
                        <tr>
                            <td>${history.source.charCode}</td>
                            <td>${history.target.charCode}</td>
                            <td>${history.amount}</td>
                            <td>${history.result}</td>
                            <td>${history.date}</td>
                        </tr>
                    </#if>
                </#list>
            </table>
        </div>
    </div>
</@c.page>