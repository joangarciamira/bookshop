<h1>Catalog Service application</h1>
<p>This application is created by following chapter 3 of the book 'Cloud Native Spring in Action'. It consists on an REST API that can interact with Books, with the methods GET, POST, PUT and DELETE. </p>
<p>Unit and integration tests have also been implemented, as well as JSON ones.</p>
<p>In addition, we model the commit stage of our deployment pipeline using GitHub Actions, including test automation.</p>

<h2>Changes added (28/01/2024): Profiles branch</h2>
<p>A class for loading books was added only when the Testdata profile is active</p>

<h2>Changes added (11/02/2024): </h2>
<p>Centralized configuration server using a git repository, with refresh functionality.</p>
