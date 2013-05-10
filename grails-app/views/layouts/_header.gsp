<div id="header">
    <p><a class="header-main" href="${resource(dir: '')}">RaceTrack</a></p>

    <p class="header-sub">When's your next race?</p>

    <div id="loginHeader">
        <sec:ifLoggedIn>
            Welcome Back!
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <g:link controller='login' action='auth'>Login</g:link>
        </sec:ifNotLoggedIn>
    </div>
</div>