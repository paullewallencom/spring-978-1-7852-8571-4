QUnit.test("Create Room", function(assert) {
    var done = assert.async(), room;
    room = {
        name: "Cool Room",
        description: "A room that is very cool indeed",
        room_category_id: 1
    };
    $.ajax({
        url: "rooms",
        method: 'POST',
        data: JSON.stringify(room),
        dataType: 'json',
        success: function(response) {
            assert.ok(response.data.id, "new room created");
            done();
        },
        contentType: 'application/json'
    }).fail(function(xhr) {
        assert.ok(false, 'failed (' + xhr.status + ")");
    });
});
